package fr.Customers.Services;


import fr.Customers.Providers.CustomerProvider;
import fr.Security.RSA.Cryptography;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */

@Path("/customer")
public class CustomersServicesImpl implements CustomersServices {

    @Override
    public Response getCustomer(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            //String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            return Response.ok(CustomerProvider.findCustomerById(EncodedInfos)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response insertCustomer(String EncodedJson) {
        try {
            //Map<String, String> customer = Cryptography.dechiffrementJsonRSA(EncodedJson);
            Map<String, String> customer = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                customer.put(key, obj.getString(key));
            }
            CustomerProvider.insertCustomer(customer);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @Override
    public Response deleteCustomer(@PathParam("EncodedInfos") String EncodedInfos) {
        try {
            //String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            CustomerProvider.deleteCustomer(EncodedInfos);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateCustomer(@PathParam("EncodedInfos") String EncodedInfos, String EncodedJson) {
        try {
            //String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            //Map<String, String> customer = Cryptography.dechiffrementJsonRSA(EncodedJson);
            Map<String, String> customer = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                customer.put(key, obj.getString(key));
            }
            CustomerProvider.updateCustomer(EncodedInfos, customer);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getBalance(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            //String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            return Response.ok(CustomerProvider.findBanlanceById(EncodedInfos)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateBalance(String EncodedJson) {
        try{
            //Map<String, String> balance = Cryptography.dechiffrementJsonRSA(EncodedJson);
            Map<String, String> balance = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                balance.put(key, obj.getString(key));
            }
            CustomerProvider.updateBalance(balance);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
