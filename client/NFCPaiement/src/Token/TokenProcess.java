package Token;
import Token.HttpRequest;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Map;

/**
 * Created by ludov on 12/05/2017.
 */
public class TokenProcess {
    //TODO: Methode pour le ProcessPaiement (Web Services)

    public static void requete(String token) {
        int creditCard;
        int idC;
        int thune;
        Map<Integer,String> obj = HttpRequest. sendGet("http://localhost:8080/token/" + token);
        try {
            JSONObject result = new JSONObject(obj.get(200));
            if (obj.containsKey(200)) {
                Map<Integer,String>  obj2 = HttpRequest.sendPost("http://localhost:8080/token", token);
            }
        } catch (JSONException e) {
            System.out.println("error: " + e);
        }
    }
}
