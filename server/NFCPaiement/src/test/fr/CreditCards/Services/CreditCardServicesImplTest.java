package fr.CreditCards.Services;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.Security.RSA.Cryptography;
import junit.framework.TestCase;
import org.codehaus.jettison.json.JSONObject;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

/**
 * Created by ludov on 25/02/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CreditCardServicesImpl.class, CreditCardProviders.class})
public class CreditCardServicesImplTest {

    public Application configure() {
        return new ResourceConfig(CreditCardServicesImpl.class);
    }

    Client client;

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    @Test
    public void getAllCreditCards() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.getAllCreditCardsById(anyString())).andReturn(new ArrayList<Map<String, String>>());

        Response output = client.target("http://localhost:8080/nfcpaiement/creditcard/" + Cryptography.chiffrementRSA("1")).request().get();

        Assert.assertEquals("should return status 200", 200, output.getStatus());

        TestCase.assertNotNull("Should return list", output.getEntity());
    }

    @Test
    public void addCreditCard() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.insertCreditCard((Map<String, String>) anyObject())).andReturn(true);
        JSONObject json = new JSONObject(new HashMap<String, String>());

        Response output = client.target("http://localhost:8080/nfcpaiement/creditcard").request().post(Entity.entity(MediaType.APPLICATION_JSON_TYPE,json.toString()));

        Assert.assertEquals("should return status 201", 201, output.getStatus());
    }

    @Test
    public void getCreditCard() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.findCreditCardById(anyInt(),anyString())).andReturn(new HashMap<String, String>());

        Response output = client.target("http://localhost:8080/nfcpaiement/creditcard").request().get();

        Assert.assertEquals("should return status 200", 200, output.getStatus());

        TestCase.assertNotNull("Should return list", output.getEntity());
    }

    @Test
    public void deleteCreditCard() throws Exception {
        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.deleteCreditCard(anyInt())).andReturn(true);

        Response output = client.target("http://localhost:8080/nfcpaiement/creditcard").request().delete();

        Assert.assertEquals("should return status 200", 200, output.getStatus());
    }

}