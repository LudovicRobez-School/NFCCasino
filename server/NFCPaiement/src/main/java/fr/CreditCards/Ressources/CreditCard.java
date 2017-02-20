package fr.CreditCards.Ressources;

import java.util.Date;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCard {

    private String cardNumber;
    private Date dateExpiration;
    private int cryptogram;

    public CreditCard(String cardNumber, Date dateExpiration, int cryptogram) {
        this.cardNumber = cardNumber;
        this.dateExpiration = dateExpiration;
        this.cryptogram = cryptogram;
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
