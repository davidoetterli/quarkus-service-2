package ch.hftm;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Einfache REST-Ressource zur Begrüssung.
 */
@Path("/hello")
public class GreetingResource {

    /**
     * Gibt einen Begrüssung-Text zurück.
     *
     * @return ein String mit der Begrüssung
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
