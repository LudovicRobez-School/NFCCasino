package fr.Customers.Services;

import org.json.JSONException;
import org.json.JSONObject;
import fr.Customers.Providers.CustomerProvider;

import javax.xml.ws.Response;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class CostumersServicesImpl implements CustomersServices {


    @Override
    public Response CustomerInfo(String customer) {
        try{
            return Response.ok(CustomerProvider.CustomerInfo(customer).toString()).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response insertCustomer(String customer) {
        String mail=""; String firstName=""; String lastName=""; String password=""; String country=""; String adress=""; String zipcode=""; String state="";String city="";
        try {
            JSONObject jsonObject = new JSONObject(customer);
            mail=jsonObject.getString("mail");
            firstName=jsonObject.getString("fistName");
            lastName=jsonObject.getString("lastName");
            password=jsonObject.getString("password");
            country=jsonObject.getString("country");
            adress=jsonObject.getString("adress");
            zipcode=jsonObject.getString("postal");
            state=jsonObject.getString("state");
            city=jsonObject.getString("city");
            CustomerProvider.insertCustomer(mail,firstName,lastName,password,country,adress,zipcode,state,city);
            return Response.ok().build();
        } catch (JSONException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
