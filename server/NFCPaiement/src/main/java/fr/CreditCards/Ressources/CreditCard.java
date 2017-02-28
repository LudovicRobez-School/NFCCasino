package fr.CreditCards.Ressources;

import java.util.Date;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCard {

    private String cardNumber;
    private Date dateExpiration;
    private int cryptogram;
    private String type;

    public CreditCard(String cardNumber, Date dateExpiration, int cryptogram, String type) {
        this.cardNumber = cardNumber;
        this.dateExpiration = dateExpiration;
        this.cryptogram = cryptogram;
        this.type = type;
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

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(int cryptogram) {
        this.cryptogram = cryptogram;
    }
}
