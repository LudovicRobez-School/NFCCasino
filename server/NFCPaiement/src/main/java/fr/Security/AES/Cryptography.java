package fr.Security.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

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

        byte[] bytes = null;
        try {
            SecretKeySpec specification = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher chiffreur = Cipher.getInstance("AES");
            chiffreur.init(Cipher.ENCRYPT_MODE, specification);
            bytes = chiffreur.doFinal(message.getBytes(StandardCharsets.UTF_8));
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
        byte[] bytes;
        // Dechiffrement du message
        try {
            SecretKeySpec specification = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher dechiffreur = Cipher.getInstance("AES");
            dechiffreur.init(Cipher.DECRYPT_MODE, specification);
            bytes = dechiffreur.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return new String(bytes);
        } catch (Exception e) {
            return null;
        }
    }

}
