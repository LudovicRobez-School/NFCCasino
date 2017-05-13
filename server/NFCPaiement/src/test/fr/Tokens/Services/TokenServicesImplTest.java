package fr.Tokens.Services;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.Customers.Providers.CustomerProvider;
import fr.Security.RSA.Cryptography;
import fr.Tokens.Providers.TokensProvider;
import junit.framework.TestCase;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

/**
 * Created by ludov on 12/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({  TokenServicesImpl.class, TokensProvider.class, CustomerProvider.class, CreditCardProviders.class})
public class TokenServicesImplTest {

    Client client;

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }


    @Test
    public void addPaiement() throws Exception {
        PowerMock.mockStatic(TokensProvider.class);
        expect(TokensProvider.insertToken((Map<String, String>) anyObject())).andReturn(true);
        JSONObject json = new JSONObject(new HashMap<String, String>());

        Response output = client.target("http://localhost:8080/nfcpaiement/token").request().post(Entity.entity(MediaType.APPLICATION_JSON_TYPE,json.toString()));

        Assert.assertEquals("should return status 201", 201, output.getStatus());

        TestCase.assertNotNull("Should return String", output.getEntity());
    }

    @Test
    public void getPaiement() throws Exception {
        PowerMock.mockStatic(TokensProvider.class);
        PowerMock.mockStatic(CustomerProvider.class);
        PowerMock.mockStatic(CreditCardProviders.class);
        expect(TokensProvider.insertToken((Map<String, String>) anyObject())).andReturn(true);
        expect(CustomerProvider.findCustomerById(anyString())).andReturn(new HashMap<String, String>());
        expect(CreditCardProviders.findCreditCardById(anyInt(),anyString())).andReturn(new HashMap<String, String>());

        Response output = client.target("http://localhost:8080/nfcpaiement/token/" + Cryptography.chiffrementRSA("test")).request().get();

        Assert.assertEquals("should return status 202", 202, output.getStatus());
    }

    @Test
    public void deletePaiement() throws Exception {
        PowerMock.mockStatic(TokensProvider.class);
        expect(TokensProvider.deleteToken(anyString())).andReturn(true);

        Response output = client.target("http://localhost:8080/nfcpaiement/token/" + Cryptography.chiffrementRSA("test")).request().delete();

        Assert.assertEquals("should return status 202", 202, output.getStatus());
    }

}