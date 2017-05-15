package fr.Security.AES;


import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Cipher;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

/**
  * Created by ludov on 25/02/2017.
 */
public class CryptographyTest {

    Cipher cipher;

    @Before
    public void setUp() throws Exception {
        cipher = EasyMock.createMock(Cipher.class);
    }

    @Test
    public void chiffrementAES() throws Exception {
        expect(cipher.doFinal((byte[]) anyObject())).andReturn("test".getBytes());
        Assert.assertEquals("should return the string", "test", Cryptography.chiffrementAES("test"));
    }

    @Test
    public void dechiffrementAES() throws Exception {
        expect(cipher.doFinal((byte[]) anyObject())).andReturn("test".getBytes());
        Assert.assertEquals("should return the string", "test", Cryptography.dechiffrementAES("test"));
    }
}