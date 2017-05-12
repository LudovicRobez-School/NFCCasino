package fr.Customers.Services;


import com.owlike.genson.Genson;
import com.sun.prism.PhongMaterial;
import fr.Customers.Providers.CustomerProvider;
import fr.Security.RSA.Cryptography;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */

@Path("/customer")
public class CustomersServicesImpl implements CustomersServices {

    @Override
    public Response getCustomer(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            return Response.ok(CustomerProvider.findCustomerById(customerId)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response insertCustomer(String EncodedJson) {
        try {
            Map<String, String> customer = Cryptography.dechiffrementJsonRSA(EncodedJson);
            CustomerProvider.insertCustomer(customer);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @Override
    public Response deleteCustomer(@PathParam("EncodedInfos") String EncodedInfos) {
        try {
            String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            CustomerProvider.deleteCustomer(customerId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateCustomer(@PathParam("EncodedInfos") String EncodedInfos, String EncodedJson) {
        try {
            String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            Map<String, String> customer = Cryptography.dechiffrementJsonRSA(EncodedJson);
            CustomerProvider.updateCustomer(customerId, customer);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getBalance(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            return Response.ok(CustomerProvider.findBanlanceById(customerId)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateBalance(String EncodedJson) {
        try{
            Map<String, String> balance = Cryptography.dechiffrementJsonRSA(EncodedJson);
            CustomerProvider.updateBalance(balance);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
