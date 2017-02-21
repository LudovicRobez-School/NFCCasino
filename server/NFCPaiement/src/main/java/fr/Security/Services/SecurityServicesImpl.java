package fr.Security.Services;

import fr.Security.AES.Cryptography;
import fr.Security.RSA.RSAKeyManagement;

import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 21/02/2017.
 */
public class SecurityServicesImpl implements SecurityServices {

   private static final String publicKeyFile = "";

    @Override
    public Response getPublicKey() {
        try {
            String encodedPublicKey =Cryptography.Chiffrement(RSAKeyManagement.lectureClePublique(publicKeyFile).toString());
            return Response.ok(encodedPublicKey).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }
}
