package com.example.asus.nfccasino;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.asus.nfccasino.AES.Cryptography;
import com.example.asus.nfccasino.RSA.CryptoRSA;

/**
 * @author Gregory Vesic
 * @version 20/04/2017
 */
class User implements Parcelable {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String zipcode;
    private String city;
    private String state;
    private String country;
    private double balance;

    private String token; // plus tard

    private static String urlServer= "http://192.168.0.31:8080/"; // a modifier selon le poste server

    /**
     * Constructeur User
     */
    User() {};

    /**
     * Constructeur User
     * @param email
     */
    User(String email) {
        this.email = email;
    }

    /**
     * Méthode getEmail
     * @return
     */
    public String getEmail(){
        return email;
    }

    /**
     * Méthode setEmail
     * @param email
     */
    public void setEmail(String email){
        this.email=email;
    }

    /**
     * Méthode getFirstname
     * @return
     */
    public String getFirstname(){
        return firstname;
    }

    /**
     * Méthode setFirstname
     * @param firstname
     */
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }

    /**
     * Méthode getPassword
     * @return
     */
    public String getPassword() {return password;}

    /**
     * Méthode setPassword
     * @param password
     */
    public void setPassword(String password) {this.password = password;}

    /**
     * Méthode getLastname
     * @return
     */
    public String getLastname() {return lastname;}

    /**
     * Méthode setLastname
     * @param lastname
     */
    public void setLastname(String lastname) {this.lastname = lastname;}

    /**
     * Méthode getAddress
     * @return
     */
    public String getAddress() {return address;}

    /**
     * Méthode setAddress
     * @param address
     */
    public void setAddress(String address) {this.address = address;}

    /**
     * Méthode getZipCode
     * @return
     */
    public String getZipcode() {return zipcode;}

    /**
     * Méthode setZipcode
     * @param zipcode
     */
    public void setZipcode(String zipcode) {this.zipcode = zipcode;}

    /**
     * Méthode getCity
     * @return
     */
    public String getCity() {return city;}

    /**
     *  Méthode setCity
     * @param city
     */
    public void setCity(String city) {this.city = city;}

    /**
     * Méthode getState
     * @return
     */
    public String getState() {return state;}

    /**
     * Méthode setState
     * @param state
     */
    public void setState(String state) {this.state = state;}

    /**
     * Méthode getCountry
     * @return
     */
    public String getCountry() {return country;}

    /**
     * Méthode setCountry
     * @param country
     */
    public void setCountry(String country) {this.country = country;}

    /**
     * Méthode getBalance
     * @return
     */
    public double getBalance() {return balance;}

    /**
     * Méthode setBalance
     * @param balance
     */
    public void setBalance(double balance) {this.balance = balance;}

    /**
     * Méthode getToken
     * @return
     */
    public String getToken(){
        return token;
    }

    /**
     * Méthode setToken
     * @param token
     */
    public void setToken(String token) {this.token = token;}

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
        out.writeString(email);
        out.writeString(password);
        out.writeString(firstname);
        out.writeString(lastname);
        out.writeString(address);
        out.writeString(zipcode);
        out.writeString(city);
        out.writeString(state);
        out.writeString(country);
        out.writeDouble(balance);
        out.writeString(token);
    }

    /**
     * Instance CREATOR de Parcelable.Creator
     * Type User
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        /**
         * Méthode createFromParcel
         * @param in
         * @return
         */
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        /**
         * Méthode newArray
         * @param size
         * @return
         */
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Constructeur User
     * @param in
     */
    private User(Parcel in) {
        this.email = in.readString();
        this.password = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.address = in.readString();
        this.zipcode = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.balance = in.readDouble();
        this.token = in.readString();
    }

    /*** TEST ***/
    /**
     * Méthode cryptData
     */
    public void cryptData() {
        String urlCrypt = "";
        urlCrypt = Cryptography.chiffrementAES("security");
        Log.i("security", urlCrypt);
    }


    /**
     * Méthode getCustomer
     * @param email
     * @return
     */
    public static String getCustomer(String email) {
        String urlCrypt = "";
        urlCrypt = CryptoRSA.chiffrementRSA(email);
        String URL  = urlServer + "customer/" + urlCrypt;

        Http requete = new Http();
        requete.execute("GET",URL,"");
        String result ="";
        try {
            result = requete.get();
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }

        // Décrypter le résultat

        if(!result.equals("")) {
            Log.i("Public key",result);
            return result;
        } else {
            return "Erreur de récupération des informations";
        }
    }

    /**
     * Méthode checkCustomer
     * @param user
     * @param email
     * @param password
     * @return
     */
    public static boolean checkCustomer(User user, String email, String password) {
        if(!user.getCustomer(user.getEmail()).equals("")&&!user.getCustomer(user.getEmail()).equals("-1")) {
            try {
                JSONObject jsonProfil = new JSONObject(getCustomer(user.getEmail()));

                if (email == jsonProfil.getString("mail") && password == jsonProfil.getString("password")) {
                    Log.i("check", "ok");
                    return true;//"Authenfication réussie !";
                } else {
                    Log.i("check", "no");
                    return false;//"Authentification échouée !";
                }
            } catch (JSONException e) {
                Log.i("error json", e.toString());
                return false;//"Erreur d'authentification !";
            }
        } else {
            Log.i("check", "error");
            return false;//"Email invalide !";
        }
    }

    /**
     * Méthode insertCustomer
     * @param mail
     * @param password
     * @param firstname
     * @param lastname
     * @param address
     * @param zipcode
     * @param city
     * @param state
     * @param country
     * @param balance
     * @return
     */
    public static boolean insertCustomer(String mail, String password, String firstname, String lastname, String address, String zipcode, String city, String state, String country, String balance) throws JSONException {
        String paramsCrypt = "";
        String params= "";
        JSONObject jsonParams = new JSONObject();
        String URL  = urlServer + "customer/";

        jsonParams.put("mail", mail);
        jsonParams.put("firstname", firstname);
        jsonParams.put("laststname", lastname);
        jsonParams.put("address", address);
        jsonParams.put("zipcode", zipcode);
        jsonParams.put("city", city);
        jsonParams.put("state", state);
        jsonParams.put("country", country);
        jsonParams.put("balance", balance);

        params = jsonParams.toString();
        //paramsCrypt = CryptoRSA.chiffrementRSA(params);

        Http requete = new Http();
        requete.execute("POST",URL, params);
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
     * Méthode getBalance
     * @param email
     * @return
     */
    public static String getBalance(String email) {
        String urlCrypt = "";
        urlCrypt = CryptoRSA.chiffrementRSA(email);
        String URL  = urlServer + "customer/balance/" + urlCrypt;

        Http requete = new Http();
        requete.execute("GET",URL,"");
        String result ="";
        try {
            result = requete.get();
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }

        // Décrypter le résultat

        if(!result.equals("")) {
            Log.i("Balance",result);
            return result;
        } else {
            return "Erreur de récupération des informations";
        }
    }

    /**
     * Méthode updateBalance
     * @param email
     * @param balance
     * @return
     */
    public static boolean updateBalance(String email, double balance) throws JSONException {
        String params = "";
        String paramsCrypt= "";
        JSONObject jsonParams = new JSONObject();
        String URL  = urlServer + "customer/balance/";

        jsonParams.put("mail", email);
        jsonParams.put("balance", String.valueOf(balance));

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
        catch(Exception e ) {
            Log.i("error", e.toString());
            return false;
        }
    }

    /**
     * Méthode initProfil
     * @param user
     */
    public void initProfil(User user) {
        if(!user.getCustomer(user.getEmail()).equals("")&&!user.getCustomer(user.getEmail()).equals("-1")) {
            try {
                JSONObject jsonProfil = new JSONObject(getCustomer(user.getEmail()));
                user.setEmail(jsonProfil.getString("mail"));
                user.setPassword(jsonProfil.getString("password"));
                user.setFirstname(jsonProfil.getString("firstname"));
                user.setLastname(jsonProfil.getString("lastname"));
                user.setAddress(jsonProfil.getString("address"));
                user.setZipcode(jsonProfil.getString("zipcode"));
                user.setCity(jsonProfil.getString("city"));
                user.setState(jsonProfil.getString("state"));
                user.setCountry(jsonProfil.getString("country"));
                user.setBalance(jsonProfil.getDouble("balance"));
            } catch (JSONException e) {
                Log.i("error json", e.toString());
            }
        }
    }
}
