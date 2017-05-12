package com.example.asus.nfccasino;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.asus.nfccasino.AES.Cryptography;

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

    private String urlServer= "http://192.168.0.31:8080/"; // a modifié selon le poste server

    User() {};
    User(String email) {
        this.email = email;
    }


    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getZipcode() {return zipcode;}
    public void setZipcode(String zipcode) {this.zipcode = zipcode;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}

    public String getToken(){
        return token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
    public void cryptData() {
        String urlCrypt = "";
        urlCrypt = Cryptography.chiffrementAES("security");
        Log.i("security", urlCrypt);
    }

    public boolean getPublicKey() {
        String URL  = urlServer + "security";

        Http requete = new Http();
        requete.execute("GET",URL,"");
        String result ="";
        try {
            result = requete.get();
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }
        if(!result.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public String getCustomer(String email) {
        String urlCrypt = "";
        urlCrypt = Cryptography.chiffrementAES("security");
        String URL  = urlServer + "security/" + urlCrypt;

        Http requete = new Http();
        requete.execute("GET",URL,"");
        String result ="";
        try {
            result = requete.get();
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }
        if(!result.equals("")) {
            Log.i("Public key",result);
            return result;
        } else {
            return "Erreur de récupération des informations";
        }
    }

    public boolean checkCustomer(User user, String email, String password) {
        if(!user.getCustomer(user.getEmail()).equals("")&&!user.getCustomer(user.getEmail()).equals("-1")) {
            try {
                JSONObject jsonProfil = new JSONObject(getCustomer(user.getEmail()));

                if (email == jsonProfil.getString("mail") && password == jsonProfil.getString("password")) {
                    return true;//"Authenfication réussie !";
                } else {
                    return false;//"Authentification échouée !";
                }
            } catch (JSONException e) {
                Log.i("error json", e.toString());
                return false;//"Erreur d'authentification !";
            }
        } else {
            return false;//"Email invalide !";
        }
    }

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
