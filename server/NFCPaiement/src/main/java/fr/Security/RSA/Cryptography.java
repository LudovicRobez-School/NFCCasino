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
import java.util.Map;

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

        // Chargement du message chiffré
        byte[] messageCode = null;
        try {
            FileInputStream fichier = new FileInputStream(encodedCode);
            messageCode = new byte[fichier.available()];
            fichier.read(messageCode);
            fichier.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du message : " + e);
            System.exit(-1);
        }

        // Déchiffrement du message
        byte[] bytes = null;
        try {
            Cipher dechiffreur = Cipher.getInstance("RSA");
            dechiffreur.init(Cipher.DECRYPT_MODE, clePrivee);
            bytes = dechiffreur.doFinal(messageCode);
        } catch(NoSuchAlgorithmException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(NoSuchPaddingException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(InvalidKeyException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(IllegalBlockSizeException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(BadPaddingException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        }

        // Affichage du message
        String message = new String(bytes);
        System.out.println("Message : " + message);
        return null;
    }

}
