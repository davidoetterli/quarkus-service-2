package ch.hftm.service;

import ch.hftm.dto.BlogValidationDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class BlogValidationService {

    @Incoming("blog-validation-requests")
    @Outgoing("blog-validation-responses")
    public String processValidation(String message) {
        Log.info("Validating Blog Post: " + message);
        try {
            // Simulierte Validierung (z.B. keine verbotenen Wörter)
            boolean isValid = !message.toLowerCase().contains("spam");
            BlogValidationDTO validationDTO = new BlogValidationDTO(Long.parseLong(message.split(":")[0]), isValid);

            Log.info("Validation result for Blog ID " + validationDTO.postId() + ": " + validationDTO.valid());
            return validationDTO.toMessage();
        } catch (Exception e) {
            Log.error("Error processing validation: " + message, e);
            return message.split(":")[0] + ":false"; // Falls ein Fehler auftritt, setzen wir den Status auf `false`
        }
    }
}
