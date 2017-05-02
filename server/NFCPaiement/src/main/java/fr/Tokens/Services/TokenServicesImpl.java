package fr.Tokens.Services;

import fr.Security.RSA.Cryptography;
import fr.Tokens.Providers.TokensProvider;
import fr.Tokens.Ressources.Token;
import org.codehaus.jettison.json.JSONObject;

import fr.
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Created by rl613611 on 21/02/2017.
 */
@Path("/token")
public class TokenServicesImpl implements TokenServices {
    @Override
    public Response addToken(String EncodedInfos) {
        try {
            Map<String, String> json = Cryptography.dechiffrementRSA(EncodedInfos);
            Token token = new Token(Integer.parseInt(json.get("creditCardId")),Integer.parseInt(json.get("customerId")),Integer.parseInt(json.get("somme")));
            TokensProvider.insertToken(token.getCreditCardId(), token.getCustomerId(), token.getSomme(), token.getToken());
            return Response.status(Response.Status.CREATED).entity(Cryptography.chiffrementRSA(token.getToken())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getToken(String EncodedInfos) {
        CreditCard creditCard;
        Customer idC;
        int thune;
        try{
            Map<String,String> json = Cryptography.dechiffrementRSA(EncodedInfos);
            Map<String,String> result = TokensProvider.findTokenById(json.get("token"));
            Cred
                creditCard=CreditCardProviders.get sult.get("creditCardId");
                idC=result.getJSONObject(i).getInt("customerId");
                thune= result.getJSONObject(i).getInt("somme");

            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deleteToken(String EncodedInfos) {
        try{
            Map<String,String> json = Cryptography.dechiffrementRSA(EncodedInfos);
            TokensProvider.deleteToken(json.get("token"));
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
