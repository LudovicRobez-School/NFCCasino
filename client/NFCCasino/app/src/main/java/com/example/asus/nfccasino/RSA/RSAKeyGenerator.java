package com.example.asus.nfccasino.RSA;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author Cyril Rabat
 * @version 19/10/2015
 */
public class RSAKeyGenerator {

    /**
    * Méthode principale.
    * @param privateKeyFile nom du fichier dans lequel sauvegarder la clé privée
    * @param publicKeyFile nom du fichier dans lequel sauvegarder la clé publique
    */
    public static void GeneratingKeys(String privateKeyFile, String publicKeyFile){

        RefreshFile(privateKeyFile);
        RefreshFile(publicKeyFile);

        // Création d'un générateur RSA
        KeyPairGenerator generateurCles = null;
        try {
            generateurCles = KeyPairGenerator.getInstance("RSA");
            generateurCles.initialize(2048);

            // Génération de la paire de clés
            KeyPair paireCles = generateurCles.generateKeyPair();

            // Sauvegarde de la clé privée
            RSAKeyManagement.sauvegardeClePrivee(paireCles.getPrivate(), privateKeyFile);

            // Sauvegarde de la clé publique
            RSAKeyManagement.sauvegardeClePublique(paireCles.getPublic(), publicKeyFile);

        }catch (NoSuchAlgorithmException e) {

        }
    }

    public static void RefreshFile(String filePath) {
        try {
            File fichier = new File("filePath");
            fichier.delete();
            fichier.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

