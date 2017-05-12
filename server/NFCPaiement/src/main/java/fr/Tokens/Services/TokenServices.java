package fr.Tokens.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 21/02/2017.
 */
public interface TokenServices {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response addPaiement(String EncodedJson);

    @GET
    @Path("/{EncodedInfos}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPaiement(@PathParam("EncodedInfos") String EncodedInfos);

    @DELETE
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response deletePaiement(@PathParam("EncodedInfos") String EncodedInfos);
}
