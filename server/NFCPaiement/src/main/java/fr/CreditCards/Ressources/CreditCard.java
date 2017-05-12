package fr.CreditCards.Ressources;

import java.util.Date;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCard {

    private String cardNumber;
    private int dateExpiration;
    private int cryptogram;
    private String type;

    public CreditCard(Map<String,String> creditCard) {
        this.cardNumber = creditCard.get("cardNumber");
        this.dateExpiration = Integer.parseInt(creditCard.get("dateExpiration"));
        this.cryptogram = Integer.parseInt(creditCard.get("cryptogram"));
        this.type = creditCard.get("type");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(int dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(int cryptogram) {
        this.cryptogram = cryptogram;
    }
}
