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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomer(@PathParam("EncodedInfos") String customer);

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertCustomer(String json);

    @DELETE
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteCustomer(@PathParam("EncodedInfos") String customer);

    @POST
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateCustomer(@PathParam("EncodedInfos") String EncodedInfos, String json);

}
