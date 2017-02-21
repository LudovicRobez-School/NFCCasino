package fr.Security.Services;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 21/02/2017.
 */
public interface SecurityServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getPublicKey();

}
