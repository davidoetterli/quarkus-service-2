package ch.hftm.service;

import ch.hftm.dto.ValidationRequest;
import ch.hftm.dto.ValidationResponse;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import java.util.Locale;

/**
 * Service zur Validierung von Textnachrichten.
 * Diese Klasse ist nicht zur Erweiterung vorgesehen.
 */
@ApplicationScoped
public final class ValidationService {
    /**
     * Logger für die Validierung.
     */
    private static final Logger LOG = Logger.getLogger(ValidationService.class);

    /**
     * Validiert die eingehenden Textnachrichten.
     *
     * @param requests der eingehende Stream von {@code ValidationRequest} Objekten
     * @return ein Multi-Stream von {@code ValidationResponse} Objekten
     */
    @Incoming("validation-request")
    @Outgoing("validation-response")
    public Multi<ValidationResponse> validateTextMessages(final Multi<ValidationRequest> requests) {
        return requests.onItem().transform(request -> {
            final String text = request.text();
            final String lowerText;
            if (text != null) {
                lowerText = text.toLowerCase(Locale.ROOT);
            } else {
                lowerText = "";
            }
            final boolean valid = (text != null) && (!lowerText.contains("hftm sucks"));
            LOG.debugf("Text-Validation: '%s' -> %b", text, valid);
            final long identifier = request.identifier();
            return new ValidationResponse(identifier, valid);
        });
    }
}
