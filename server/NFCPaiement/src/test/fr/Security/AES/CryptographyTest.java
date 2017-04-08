package fr.Security.AES;

import fr.Security.Services.SecurityServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.crypto.Cipher;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * Created by ludov on 25/02/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Cryptography.class, Cipher.class})
public class CryptographyTest {

    private Cipher cipher;

    @Before
    public void setUp() throws Exception {
        cipher = PowerMock.createMock(Cipher.class);
        expect(cipher.doFinal(anyString().getBytes())).andReturn("test".getBytes());
    }

    @Test
    public void chiffrementAES() throws Exception {
        PowerMock.mockStatic(Cipher.class);
        expect(Cipher.getInstance(anyString())).andReturn(cipher);
        Assert.assertEquals("should return the string", "test", Cryptography.chiffrementAES("test"));
    }

    @Test
    public void dechiffrementAES() throws Exception {
        PowerMock.mockStatic(Cipher.class);
        expect(Cipher.getInstance(anyString())).andReturn(cipher);
        Assert.assertEquals("should return the string", "test", Cryptography.dechiffrementAES("test"));
    }
}