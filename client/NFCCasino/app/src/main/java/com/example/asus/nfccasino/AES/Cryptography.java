package com.example.asus.nfccasino.AES;


import android.text.TextUtils;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

/**
 * @author Cyril Rabat
 * @version 19/10/2015
 */
public class Cryptography {

    //private static String secretKey = "SecretKeyForNFCCasino";
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
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        } catch (InvalidKeyException e) {
            return e.toString();
        } catch (NoSuchPaddingException e) {
            return e.toString();
        } catch (BadPaddingException e) {
            return e.toString();
        } catch (IllegalBlockSizeException e) {
            return e.toString();
        }
    }

    private static String getPassphraseSize16(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        char controlChar = '\u0014';
        String key16 = key + controlChar;
        if (key16.length() < 16) {
            while (key16.length() < 16) {
                key16 += key + controlChar;
            }
        }
        if (key16.length() > 16) {
            key16 = key16.substring(key16.length() - 16, key16.length());
        }
        return key16;
    }

    /**
     * Methode dechiffrement.
     * @param message message que l'on veut dechiffrer
     */
    public static String dechiffrementAES (String message) {
        SecretKeySpec specification = new SecretKeySpec(secretKey.getBytes(), "AES");
        byte[] bytes;
        // Dechiffrement du message
        try {
            Cipher dechiffreur = Cipher.getInstance("AES");
            dechiffreur.init(Cipher.DECRYPT_MODE, specification);
            bytes = dechiffreur.doFinal(message.getBytes());
            return bytes.toString();
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        } catch (NoSuchPaddingException e) {
            return e.toString();
        } catch (InvalidKeyException e) {
            return e.toString();
        } catch (BadPaddingException e) {
            return e.toString();
        }  catch (IllegalBlockSizeException e) {
            return e.toString();
        }
    }

}
