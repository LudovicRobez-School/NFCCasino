package fr.Customers.Services;


import com.owlike.genson.Genson;
import com.sun.prism.PhongMaterial;
import fr.Customers.Providers.CustomerProvider;
import fr.Security.RSA.Cryptography;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class CostomersServicesImpl implements CustomersServices {

    Genson genson = new Genson();

    @Override
    public Response getCustomer(String customerId) {
        try{
            return Response.ok(CustomerProvider.findCustomerById(customerId)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response insertCustomer(String json) {
        try {
            Map customer = genson.deserialize(json,Map.class);
            if (CustomerProvider.insertCustomer(customer)){
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @Override
    public Response deleteCustomer(@PathParam("EncodedInfos") String EncodedInfos) {
        try {
            Map<String, String> customer = Cryptography.dechiffrementRSA(EncodedInfos);
            if (CustomerProvider.deleteCustomer(customer.get("id"))){
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateCustomer(@PathParam("EncodedInfos") String EncodedInfos, String json) {
        try {
            Map<String, String> customerId = Cryptography.dechiffrementRSA(EncodedInfos);
            Map customer = genson.deserialize(json,Map.class);
            if (CustomerProvider.updateCustomer(customerId.get("id"), customer)){
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
