package fr.Tokens.Services;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.CreditCards.Ressources.CreditCard;
import fr.Customers.Providers.CustomerProvider;
import fr.Customers.Ressources.Customer;
import fr.Security.RSA.Cryptography;
import fr.Tokens.Providers.TokenProcess;
import fr.Tokens.Providers.TokensProvider;
import fr.Tokens.Ressources.Token;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by rl613611 on 21/02/2017.
 */
@Path("/token")
public class TokenServicesImpl implements TokenServices {
    @Override
    public Response addPaiement(String EncodedJson) {
        try {
            Map<String, String> json = Cryptography.dechiffrementJsonRSA(EncodedJson);
            String token = TokenProcess.generateToken();
            json.put("token", token);
            TokensProvider.insertToken(json);
            return Response.status(Response.Status.CREATED).entity(Cryptography.chiffrementRSA(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getPaiement(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            String tokenKey= Cryptography.dechiffrementPathRSA(EncodedInfos);
            Token token = new Token(TokensProvider.findTokenById(tokenKey));
            Customer customer = new Customer(CustomerProvider.findCustomerById(token.getCustomerId()));
            CreditCard creditCard = new CreditCard(CreditCardProviders.findCreditCardById(token.getCreditCardId(), token.getCustomerId()));
            TokenProcess paiementProcess = new TokenProcess();
            paiementProcess.processPaiement(paiementProcess.initPaiement(creditCard, customer, token));
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deletePaiement(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            String tokenKey = Cryptography.dechiffrementPathRSA(EncodedInfos);
            TokensProvider.deleteToken(tokenKey);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
