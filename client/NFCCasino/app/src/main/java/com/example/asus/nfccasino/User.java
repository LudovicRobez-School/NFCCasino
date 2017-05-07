package com.example.asus.nfccasino;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.asus.nfccasino.AES.Cryptography;


class User implements Parcelable {

    private String login;
    private String token;
    private String pseudo;
    private int score;
    private String team;
    private String email;
    private String name;
    private String firstname;

    User(String login) {
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login=login;
    }

    public String getToken(){
        return token;
    }

    public String getPseudo(){
        return pseudo;
    }

    public void setPseudo(String pseudo){
        this.pseudo=pseudo;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score=score;
    }

    public String getTeam(){
        return team;
    }

    public void setTeam(String team){
        this.team=team;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname=firstname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(login);
        out.writeString(token);
        out.writeString(pseudo);
        out.writeInt(score);
        out.writeString(team);
        out.writeString(email);
        out.writeString(name);
        out.writeString(firstname);
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
        this.login = in.readString();
        this.token = in.readString();
        this.pseudo = in.readString();
        this.score = in.readInt();
        this.team = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.firstname = in.readString();
    }

    public void initProfil(User user) {
        if(!user.getInfo(user.getLogin()).equals("")&&!user.getInfo(user.getLogin()).equals("-1")) {
            try {
                JSONObject jsonRanking = new JSONObject(user.getInfo(user.getLogin()));
                    user.setPseudo(jsonRanking.getString("pseudo"));
                    user.setScore(jsonRanking.getInt("score"));
                    user.setTeam(jsonRanking.getString("team"));
                    user.setEmail(jsonRanking.getString("email"));
                    user.setName(jsonRanking.getString("lastName"));
                    user.setFirstname(jsonRanking.getString("firstName"));
            } catch (JSONException e) {
                Log.i("error json", e.toString());
            }
        }
    }

    public String getInfo(String login) {
        String urlCrypt = "";
        Cryptography crypt = new Cryptography();
        crypt.chiffrementAES("security");
        String URL  = "http://localhost:8080/" + urlCrypt + login;
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
            return result;
        } else {
            return "Erreur de récupération des informations";
        }
    }
}
