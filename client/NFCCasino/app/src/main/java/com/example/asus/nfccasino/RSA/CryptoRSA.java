package com.example.asus.nfccasino.RSA;

import org.json.JSONObject;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ludov on 20/02/2017.
 * Source: http://www.cyril-rabat.fr/articles/index.php?article=53
 */
public class CryptoRSA {

   public static String chiffrementRSA (String encodedCode) {
       //Recuperation de la cle publique
       PublicKey clePublique = RSAKeyManagement.lectureClePublique(RSAKeyManagement.publicKeyFile);

       // Chiffrement du message
       byte[] bytes = null;
       try {
           Cipher chiffreur = Cipher.getInstance("RSA");
           chiffreur.init(Cipher.ENCRYPT_MODE, clePublique);
           bytes = chiffreur.doFinal(encodedCode.getBytes(StandardCharsets.UTF_8));
           return bytes.toString();
       } catch (Exception e) {
          return null;
       }
   }

    /**
    * Methode principale.
    * @param encodedCode message à déchiffrer
    */

    public static String dechiffrementPathRSA(String encodedCode) {

        // Récupération de la clé privée
        PrivateKey clePrivee = RSAKeyManagement.lectureClePrivee(RSAKeyManagement.privateKeyFile);

        // Déchiffrement du message
        byte[] bytes = encodedCode.getBytes(StandardCharsets.UTF_8);
        try {
            Cipher dechiffreur = Cipher.getInstance("RSA");
            dechiffreur.init(Cipher.DECRYPT_MODE, clePrivee);
            bytes = dechiffreur.doFinal(bytes);
            return bytes.toString();
        } catch(Exception e) {
            return null;
        }
    }

    public static Map<String, String> dechiffrementJsonRSA(String encodedCode) {

        // Récupération de la clé privée
        PrivateKey clePrivee = RSAKeyManagement.lectureClePrivee(RSAKeyManagement.privateKeyFile);

        // Déchiffrement du message
        byte[] bytes = encodedCode.getBytes();
        try {
            Cipher dechiffreur = Cipher.getInstance("RSA");
            dechiffreur.init(Cipher.DECRYPT_MODE, clePrivee);
            bytes = dechiffreur.doFinal(bytes);
            Map<String,String> mapJSON = new HashMap<String, String>();
            JSONObject obj = new JSONObject(bytes.toString());
            Iterator keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                mapJSON.put(key, obj.getString(key));
            }
            return mapJSON;
        } catch(Exception e) {
            return null;
        }
    }
    
}
