package fr.Customers.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Created by rl613611 on 17/01/2017.
 */
public interface CustomersServices {


    @GET
    @Path("/{EncodedInfos}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomer(@PathParam("EncodedInfos") String EncodedInfos);

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertCustomer(String EncodedJson);

    @DELETE
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteCustomer(@PathParam("EncodedInfos") String EncodedInfos);

    @POST
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateCustomer(@PathParam("EncodedInfos") String EncodedInfos, String EncodedJson);

    @GET
    @Path("/balance/{EncodedInfos}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getBalance(@PathParam("EncodedInfos") String EncodedInfos);

    @POST
    @Path("/balance/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateBalance(String EncodedJson);
}
