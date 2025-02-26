package ch.hftm.service;

import ch.hftm.dto.ValidationRequest;
import ch.hftm.dto.ValidationResponse;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ValidationService {
    private static final Logger LOG = Logger.getLogger(ValidationService.class);

    @Incoming("validation-request")
    @Outgoing("validation-response")
    public Multi<ValidationResponse> validateTextMessages(Multi<ValidationRequest> requests) {
        return requests.onItem().transform(request -> {
            boolean valid = request.text() != null && !request.text().toLowerCase().contains("hftm sucks");
            LOG.debugf("Text-Validation: '%s' -> %b", request.text(), valid);
            return new ValidationResponse(request.id(), valid);
        });
    }
}
