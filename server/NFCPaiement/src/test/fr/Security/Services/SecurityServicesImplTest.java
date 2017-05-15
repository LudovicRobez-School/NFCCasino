package fr.Security.Services;

import fr.CreditCards.Services.CreditCardServicesImpl;
import fr.Security.AES.Cryptography;
import fr.Security.RSA.RSAKeyManagement;
import junit.framework.TestCase;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;

/**
 * Created by ludov on 12/05/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SecurityServicesImpl.class, Cryptography.class, RSAKeyManagement.class})
public class SecurityServicesImplTest {

    public Application configure() {
        return new ResourceConfig(CreditCardServicesImpl.class);
    }

    Client client;

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    @Test
    public void getPublicKey() throws Exception {
        PowerMock.mockStatic(RSAKeyManagement.class);
        expect(RSAKeyManagement.lectureClePublique(anyString()).toString()).andReturn("test");

        Response output = client.target("http://localhost:8080/nfcpaiement/security").request().get();

        Assert.assertEquals("should return status 202", 202, output.getStatus());

        TestCase.assertNotNull("Should return Map", output.getEntity());
    }

}