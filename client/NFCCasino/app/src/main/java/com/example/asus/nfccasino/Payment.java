package com.example.asus.nfccasino;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Gregory Vesic
 * @version 13/05/2017
 */
public class Payment implements Parcelable {

    private int id;
    private int userId;
    private int creditCardId;
    private double amount;
    private String token;

    private static String urlServer= "http://192.168.0.31:8080/"; // a modifier selon le poste server

    /**
     * Méthode getId
     * @return
     */
    public int getId() {return id;}

    /**
     * Méthode setId
     * @param id
     */
    public void setId(int id) {this.id = id;}

    /**
     * Méthode getUserId
     * @return
     */
    public int getUserId() {return userId;}

    /**
     * Méthode setUserId
     * @param userId
     */
    public void setUserId(int userId) {this.userId = userId;}

    /**
     * Méthode getCreditCardId
     * @return
     */
    public int getCreditCardId() {return creditCardId;}

    /**
     * Méthode setCreditCardId
     * @param creditCardId
     */
    public void setCreditCardId(int creditCardId) {this.creditCardId = creditCardId;}

    /**
     * Méthode getAmount
     * @return
     */
    public double getAmount() {return amount;}

    /**
     * Méthode setAmount
     * @param amount
     */
    public void setAmount(double amount) {this.amount = amount;}

    /**
     * Méthode getToken
     * @return
     */
    public String getToken() {return token;}

    /**
     * Méthode setToken
     * @param token
     */
    public void setToken(String token) {this.token = token;}

    /**
     * Constructeur Payment
     */
    Payment() {};

    /**
     * Constructeur Payment
     * @param id
     * @param userId
     * @param creditCardId
     * @param amount
     * @param token
     */
    Payment(int id, int userId, int creditCardId, double amount, String token) {
        this.id = id;
        this.userId = userId;
        this.creditCardId = creditCardId;
        this.amount = amount;
        this.token = token;
    }

    /**
     * Méthode describeContents
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Méthode writeToParcel
     * @param out
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(userId);
        out.writeInt(creditCardId);
        out.writeDouble(amount);
        out.writeString(token);
    }

    /**
     * Instance CREATOR de Parcelable.Creator
     * Type Payment
     */
    public static final Parcelable.Creator<Payment> CREATOR = new Parcelable.Creator<Payment>() {
        /**
         * Méthode createFromParcel
         * @param in
         * @return
         */
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        /**
         * Méthode newArray
         * @param size
         * @return
         */
        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    /**
     * Constructeur Payment
     * @param in
     */
    private Payment(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.creditCardId = in.readInt();
        this.amount = in.readDouble();
        this.token = in.readString();
    }

    /**
     * Méthode addPayment
     * @param userId
     * @param creditCardId
     * @param amount
     * @param uuid
     * @return
     * @throws JSONException
     */
    public static boolean addPayment(int userId, int creditCardId, int amount, int uuid) throws JSONException { // UUID = ????
        String params = "";
        String paramsCrypt= "";
        JSONObject jsonParams = new JSONObject();
        String URL  = urlServer + "token/";

        jsonParams.put("customerId", Integer.valueOf(userId));
        jsonParams.put("creditCardId", Integer.valueOf(creditCardId));
        jsonParams.put("amount", Integer.valueOf(amount));
        jsonParams.put("uuid", Integer.valueOf(uuid));

        params = jsonParams.toString();
        //paramsCrypt = CryptoRSA.chiffrementRSA(email);

        Http requete = new Http();
        requete.execute("POST",URL,params);
        String result ="";
        try {
            result = requete.get();
            Log.i("result", result);
            return true;
        }
        catch(Exception e ){
            Log.i("error", e.toString());
            return false;
        }
    }


    public String getToken(String id) {
        String params = "";
        // encoder id dans params

        String URL  = urlServer + "creditcard/" + params;
        String result =""; // creditCardId , customerId

        Http requete = new Http();
        requete.execute("GET",URL,"");
        try {
            result = requete.get();
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }

        // Décrypter le résultat

        if(!result.equals("")) {
            return result;
        } else {
            return "Erreur de récupération des données";
        }
    }
}

/*

p_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  p_c_id int(11) NOT NULL,
  p_b_id int(11) NOT NULL,
  p_amount float(11) NOT NULL,
  p_token varchar(255) COLLATE utf8_unicode_ci,
 */
