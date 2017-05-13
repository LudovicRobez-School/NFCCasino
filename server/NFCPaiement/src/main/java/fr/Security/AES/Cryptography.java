package fr.Security.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Cyril Rabat
 * @version 19/10/2015
 */
public class Cryptography {

    private static String secretKey = "SecretKeyForNFCC";

    /**
     * Methode chiffrement.
     * @param message message que l'on veut chiffrer
     */
    public static String chiffrementAES(String message) {
        SecretKeySpec specification = new SecretKeySpec(secretKey.getBytes(), "AES");
        byte[] bytes = null;
        try {
            Cipher chiffreur = Cipher.getInstance("AES");
            chiffreur.init(Cipher.ENCRYPT_MODE, specification);
            bytes = chiffreur.doFinal(message.getBytes());
            return bytes.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Methode dechiffrement.
     * @param message message que l'on veut dechiffrer
     */
    public static String dechiffrementAES (String message) {
        SecretKeySpec specification = new SecretKeySpec(secretKey.getBytes(), "AES");
        byte[] bytes = null;
        // Dechiffrement du message
        try {
            Cipher dechiffreur = Cipher.getInstance("AES");
            dechiffreur.init(Cipher.DECRYPT_MODE, specification);
            bytes = dechiffreur.doFinal(message.getBytes());
            return bytes.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
