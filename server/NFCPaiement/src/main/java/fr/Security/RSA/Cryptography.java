package fr.Security.RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import java.util.*;

/**
 * Created by ludov on 20/02/2017.
 * Source: http://www.cyril-rabat.fr/articles/index.php?article=53
 */
public class Cryptography {

    private static final String privateKeyFile = "";
    private static final String publicKeyFile = "";

   public static String chiffrementRSA (String encodedCode) {
       //Recuperation de la cle publique
       PublicKey clePublique = RSAKeyManagement.lectureClePublique(publicKeyFile);

       // Chiffrement du message
       byte[] bytes = null;
       try {
           Cipher chiffreur = Cipher.getInstance("RSA");
           chiffreur.init(Cipher.ENCRYPT_MODE, clePublique);
           bytes = chiffreur.doFinal(encodedCode.getBytes());
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
        PrivateKey clePrivee = RSAKeyManagement.lectureClePrivee(privateKeyFile);

        // Déchiffrement du message
        byte[] bytes = encodedCode.getBytes();
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
        PrivateKey clePrivee = RSAKeyManagement.lectureClePrivee(privateKeyFile);

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
