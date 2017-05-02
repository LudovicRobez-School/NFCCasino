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
    Response CustomerInfo(String customer);

    @POST
    @Path("/{EncodedInfos}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertCustomer(String customer );
}
