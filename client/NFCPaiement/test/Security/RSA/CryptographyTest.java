package Security.RSA;


import Security.RSA.Cryptography;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Cipher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.easymock.EasyMock.anyObject;
import static org.junit.Assert.*;

/**
 * Created by ludov on 12/05/2017.
 */
public class CryptographyTest {

    private Cipher cipher;

    @Before
    public void setUp() throws Exception {
        cipher = EasyMock.createMock(Cipher.class);
    }

    @Test
    public void chiffrementRSA() throws Exception {
        EasyMock.expect(cipher.doFinal(anyObject())).andReturn("test".getBytes());
        Assert.assertEquals("should return the string", "test", Cryptography.chiffrementRSA("test"));
    }

    @Test
    public void dechiffrementPathRSA() throws Exception {
        EasyMock.expect(cipher.doFinal(anyObject())).andReturn("test".getBytes());
        Assert.assertEquals("should return the string", "test", Cryptography.dechiffrementPathRSA("test"));
    }

    @Test
    public void dechiffrementJsonRSA() throws Exception {
        Map<String,String> json = new HashMap<String, String>();
        json.put("test","test");
        EasyMock.expect(cipher.doFinal(anyObject())).andReturn("{\"test\": test}".getBytes());
        Assert.assertEquals("should return the string", json, Cryptography.dechiffrementJsonRSA("test"));
    }

}