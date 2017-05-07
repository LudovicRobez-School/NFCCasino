package fr.Tokens.Services;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.CreditCards.Ressources.CreditCard;
import fr.Customers.Providers.CustomerProvider;
import fr.Customers.Ressources.Customer;
import fr.Security.RSA.Cryptography;
import fr.Tokens.Providers.PaiementProcess;
import fr.Tokens.Providers.TokensProvider;
import fr.Tokens.Ressources.Token;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Map;

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
        try{
            Map<String,String> json = Cryptography.dechiffrementRSA(EncodedInfos);
            Map<String,String> resultToken = TokensProvider.findTokenById(json.get("token"));
            Map<String,String> resultCreditCard = CreditCardProviders.findCreditCardById(Integer.parseInt(resultToken.get("creditCardId")), Integer.parseInt(resultToken.get("customerId")));
            Map<String,String> resultCustomer = CustomerProvider.CustomerInfo(resultToken.get("customerId"));
            CreditCard creditCard = new CreditCard(resultCreditCard.get("cardNumber"), Integer.parseInt(resultCreditCard.get("dateExpiration")), Integer.parseInt(resultCreditCard.get("cryptogram")), resultCreditCard.get("type"));
            Customer customer = new Customer(resultCustomer.get("mail"), resultCustomer.get("firstName"), resultCustomer.get("lastName"), resultCustomer.get("city"), resultCustomer.get("country"), resultCustomer.get("address"), resultCustomer.get("postalCode"), resultCustomer.get("state"));
            new PaiementProcess().initPaiement(creditCard, customer, Integer.parseInt(resultToken.get("somme")));
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
