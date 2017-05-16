package fr.CreditCards.Services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.Security.RSA.Cryptography;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */

@Path("/creditcard")
public class CreditCardServicesImpl implements CreditCardServices{

    @Override
    public Response getAllCreditCards(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            //String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            ArrayList<Map<String, String>> allCreditCard = CreditCardProviders.getAllCreditCardsById(EncodedInfos);
            JSONArray jsonAllCreditCard = new JSONArray(allCreditCard);
            return Response.ok(jsonAllCreditCard.toString()).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response addCreditCard(String EncodedJson) {
        try {
            //Map<String, String> creditCard = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            Map<String, String> creditCard = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                creditCard.put(key, obj.getString(key));
            }
            CreditCardProviders.insertCreditCard(creditCard);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getCreditCard(String EncodedJson) {
        try{
            //Map<String,String> json = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            Map<String, String> json = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                json.put(key, obj.getString(key));
            }
            Map<String, String> creditCard = CreditCardProviders.findCreditCardById(Integer.parseInt(json.get("creditCardId")), json.get("customerId"));
            JSONObject jsonCreditCard = new JSONObject(creditCard);
            return Response.ok(jsonCreditCard.toString()).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deleteCreditCard(String EncodedJson) {
        try{
            //Map<String,String> json = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            Map<String, String> json = new HashMap<>();
            JSONObject obj = new JSONObject(EncodedJson);
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                json.put(key, obj.getString(key));
            }
            CreditCardProviders.deleteCreditCard(Integer.parseInt(json.get("creditCardId")));
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
