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
class CreditCard implements Parcelable {
    private int id;
    private int userId;
    private int number;
    private int expDate;
    private int ctrlNumber;
    private String type;

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
     * Méthode getNumber
     * @return
     */
    public int getNumber() {return number;}

    /**
     * Méthode setNumber
     * @param number
     */
    public void setNumber(int number) {this.number = number;}

    /**
     * Méthode getExpDate
     * @return
     */
    public int getExpDate() {return expDate;}

    /**
     * Méthode setExpDate
     * @param expDate
     */
    public void setExpDate(int expDate) {this.expDate = expDate;}

    /**
     * Méthode getCtrlNumber
     * @return
     */
    public int getCtrlNumber() {return ctrlNumber;}

    /**
     * Méthode setCtrlNumber
     * @param ctrlNumber
     */
    public void setCtrlNumber(int ctrlNumber) {this.ctrlNumber = ctrlNumber;}

    /**
     * Méthode getType
     * @return
     */
    public String getType() {return type;}

    /**
     * Méthode setType
     * @param type
     */
    public void setType(String type) {this.type = type;}

    /**
     * Constructeur CreditCard
     */
    CreditCard() {};

    /**
     * Constructeur CreditCard
     * @param id
     * @param userId
     * @param number
     * @param expDate
     * @param ctrlNumber
     * @param type
     */
    CreditCard(int id, int userId, int number, int expDate, int ctrlNumber, String type) {
        this.id = id;
        this.userId = userId;
        this.number = number;
        this.expDate = expDate;
        this.ctrlNumber = ctrlNumber;
        this.type = type;
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
        out.writeInt(number);
        out.writeInt(expDate);
        out.writeInt(ctrlNumber);
        out.writeString(type);
    }

    /**
     * Instance CREATOR de Parcelable.Creator
     * Type CreditCard
     */
    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() {
        /**
         * Méthode createFromParcel
         * @param in
         * @return
         */
        @Override
        public CreditCard createFromParcel(Parcel in) {
            return new CreditCard(in);
        }

        /**
         * Méthode newArray
         * @param size
         * @return
         */
        @Override
        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };

    /**
     * Constructeur CreditCard
     * @param in
     */
    private CreditCard(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.number = in.readInt();
        this.expDate = in.readInt();
        this.ctrlNumber = in.readInt();
        this.type = in.readString();
    }

    /**
     * Méthode addCreditCard
     * @param userId
     * @param number
     * @param expDate
     * @param ctrlNumber
     * @param type
     * @return
     * @throws JSONException
     */
    public static boolean addCreditCard(int userId, int number, int expDate, int ctrlNumber, String type) throws JSONException {
        String params = "";
        String paramsCrypt= "";
        JSONObject jsonParams = new JSONObject();
        String URL  = urlServer + "creditcard/";

        jsonParams.put("customerId", Integer.valueOf(userId));
        jsonParams.put("cardNumber", Integer.valueOf(number));
        jsonParams.put("dateExpiration", Integer.valueOf(expDate));
        jsonParams.put("cryptogram", Integer.valueOf(ctrlNumber));
        jsonParams.put("type", type);

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

    /**
     * Méthode getCreditCard
     * @param id
     * @return
     */
    public String getCreditCard(String id) {
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

    /**
     * Méthode getAllCreditCards
     * @param userId
     * @return
     */
    public String getAllCreditCards(String userId) {
        String params = "";
        // encoder userId dans params

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

    /**
     * Méthode deleteCreditCard
     * @param id
     * @return
     */
    public boolean deleteCreditCard(String id) {
        String params = "";
        String result ="";
        // encoder id dans params

        String URL  = urlServer + "creditcard/" + params;

        Http requete = new Http();
        requete.execute("GET",URL,""); // GET -> DELETE
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

}