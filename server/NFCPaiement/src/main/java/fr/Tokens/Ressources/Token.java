package fr.Tokens.Ressources;

import java.util.Map;
import java.util.UUID;

/**
 * Created by rl613611 on 21/02/2017.
 */
public class Token {

    String token;
    int creditCardId;
    String customerId;
    int somme;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public Token(Map<String,String> token) {
        this.token = token.get("token");
        this.creditCardId = Integer.parseInt(token.get("creditCardId"));
        this.customerId = token.get("customerId");
        this.somme = Integer.parseInt(token.get("somme"));
    }
}
