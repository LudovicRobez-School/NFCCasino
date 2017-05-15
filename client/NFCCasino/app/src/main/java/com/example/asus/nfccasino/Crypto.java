package com.example.asus.nfccasino;

import android.util.Log;

/**
 * Created by Greg on 14/05/2017.
 */

public class Crypto {
    private String publicKeyRSAEnc;
    private String publicKeyRSA;

    private static String urlServer= "http://192.168.0.31:8080/"; // a modifier selon le poste server

    public String getPublicKeyRSAEnc() {return publicKeyRSAEnc;}
    public void setPublicKeyRSAEnc(String publicKeyRSAEnc) {this.publicKeyRSAEnc = publicKeyRSAEnc;}

    public String getPublicKeyRSA() {return publicKeyRSA;}
    public void setPublicKeyRSA(String publicKeyRSA) {this.publicKeyRSA = publicKeyRSA;}


    /**
     * Méthode getPublicKey
     * @return
     */
    public static String getPublicKey() {
        String URL  = urlServer + "nfcpaiement/security/";

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
            return "Erreur données vides";
        }
    }


}
