package fr.CreditCards.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 07/02/2017.
 */
public interface CreditCardServices {

    @GET
    @Path("/{EncodedInfos}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllCreditCards(@PathParam("EncodedInfos") String EncodedInfos);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response addCreditCard(String EncodedInfos);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getCreditCard(String EncodedInfos);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteCreditCard(String EncodedInfos);

}
