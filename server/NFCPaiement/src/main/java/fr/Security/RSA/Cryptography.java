package fr.Security.RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

/**
 * Created by ludov on 20/02/2017.
 * Source: http://www.cyril-rabat.fr/articles/index.php?article=53
 */
public class Cryptography {

   public static String Chiffrement (String publicKey, String encodedCode) {
       //Recuperation de la cle publique
       PublicKey clePublique = RSAKeyManagement.lectureClePublique(publicKey);

       // Chiffrement du message
       byte[] bytes = null;
       try {
           Cipher chiffreur = Cipher.getInstance("RSA");
           chiffreur.init(Cipher.ENCRYPT_MODE, clePublique);
           bytes = chiffreur.doFinal(encodedCode.getBytes());
           return bytes.toString();
       } catch (BadPaddingException e) {
          return null;
       } catch (IllegalBlockSizeException e) {
           return null;
       } catch (NoSuchPaddingException e) {
           return null;
       } catch (InvalidKeyException e) {
           return null;
       } catch (NoSuchAlgorithmException e) {
           return null;
       }
   }

    /**
    * Methode principale.
    * @param privateKey nom du fichier dans lequel se trouve la clé privée
    * @param encodedCode message à déchiffrer
    */

    public static Map<String, String> Dechiffrement(String privateKey, String encodedCode) {

        // Récupération de la clé privée
        PrivateKey clePrivee = RSAKeyManagement.lectureClePrivee(privateKey);

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
                mapJSON.put(key, obj.get(key));
            }
            return mapJSON;
        } catch(NoSuchAlgorithmException e) {
            return null;
        } catch(NoSuchPaddingException e) {
            return null;
        } catch(InvalidKeyException e) {
            return null;
        } catch(IllegalBlockSizeException e) {
            return null;
        } catch(BadPaddingException e) {
            return null;
        }

    }

}
