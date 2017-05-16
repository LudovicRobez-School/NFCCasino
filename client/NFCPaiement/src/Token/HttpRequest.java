package Token;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jordan on 31/01/2017.
 */
public class HttpRequest {
    private static final Logger logger = Logger.getLogger(HttpRequest.class.getName());

    private HttpRequest(){}

    public static Map<Integer, String> sendGet(String url) {
        Map<Integer, String> reponse = new HashMap<Integer, String>();
        int responseCode = 0;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            reponse.put(responseCode, response.toString());

            in.close();
            return reponse;
        } catch (Exception e) {
            if(responseCode>400) {
                reponse.put(responseCode, "");
                return reponse;
            }else {
                logger.log(Level.SEVERE, "", e);
            }
        }
        return reponse;
    }

    public static Map<Integer, String> sendPost(String url, String param) {
        Map<Integer, String> reponse = new HashMap<Integer, String>();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            String urlParameters = param;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            reponse.put(responseCode, response.toString());
            //print result
            return reponse;
        }catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        return reponse;
    }
}