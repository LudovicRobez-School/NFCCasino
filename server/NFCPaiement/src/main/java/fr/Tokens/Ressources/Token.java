package fr.Tokens.Ressources;

import java.util.UUID;

/**
 * Created by rl613611 on 21/02/2017.
 */
public class Token {

    String token;
    int creditCardId;
    int customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public Token(int creditCardId, int customerId, int somme) {
        this.token = UUID.randomUUID().toString();
        this.creditCardId = creditCardId;
        this.customerId = customerId;
        this.somme = somme;
    }
}
