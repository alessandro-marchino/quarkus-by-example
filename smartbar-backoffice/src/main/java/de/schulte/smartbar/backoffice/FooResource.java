package de.schulte.smartbar.backoffice;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/foo")
public class FooResource {

    public final String myText;

    /**
     * @param myText
     */
    public FooResource(@ConfigProperty(name="smartbar.backoffice.foo", defaultValue = "bar") String myText) {
        this.myText = myText;
    }

    @GET
    public Response foo() {
        return Response.ok(myText).build();
    }
}
