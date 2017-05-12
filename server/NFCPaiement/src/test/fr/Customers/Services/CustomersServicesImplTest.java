package fr.Customers.Services;


import fr.CreditCards.Services.CreditCardServicesImpl;
import fr.Customers.Providers.CustomerProvider;
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
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

/**
 * Created by ludov on 12/05/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CustomersServicesImpl.class, CustomerProvider.class})
public class CustomersServicesImplTest {
    public Application configure() {
        return new ResourceConfig(CreditCardServicesImpl.class);
    }

    Client client;

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    @Test
    public void getCustomer() throws Exception {

        PowerMock.mockStatic(CustomerProvider.class);
        expect(CustomerProvider.findCustomerById(anyString())).andReturn(new HashMap<String, String>());

        Response output = client.target("http://localhost:8080/nfcpaiement/customer/" + Cryptography.chiffrementRSA("test")).request().get();

        Assert.assertEquals("should return status 202", 202, output.getStatus());

        TestCase.assertNotNull("Should return Map", output.getEntity());
    }

    @Test
    public void insertCustomer() throws Exception {

        PowerMock.mockStatic(CustomerProvider.class);
        expect(CustomerProvider.insertCustomer((Map<String, String>) anyObject())).andReturn(true);
        JSONObject json = new JSONObject(new HashMap<String, String>());
        Response output = client.target("http://localhost:8080/nfcpaiement/customer").request().post(Entity.entity(MediaType.APPLICATION_JSON_TYPE,json.toString()));

        Assert.assertEquals("should return status 201", 201, output.getStatus());
    }

    @Test
    public void deleteCustomer() throws Exception {
        PowerMock.mockStatic(CustomerProvider.class);
        expect(CustomerProvider.deleteCustomer(anyString())).andReturn(true);
        Response output = client.target("http://localhost:8080/nfcpaiement/customer/" + Cryptography.chiffrementRSA("test")).request().delete();

        Assert.assertEquals("should return status 202", 202, output.getStatus());
    }

    @Test
    public void updateCustomer() throws Exception {
        PowerMock.mockStatic(CustomerProvider.class);
        expect(CustomerProvider.updateCustomer(anyString(),(Map<String, String>) anyObject())).andReturn(true);
        JSONObject json = new JSONObject(new HashMap<String, String>());
        Response output = client.target("http://localhost:8080/nfcpaiement/customer/" + Cryptography.chiffrementRSA("test")).request().post(Entity.entity(MediaType.APPLICATION_JSON_TYPE,json.toString()));

        Assert.assertEquals("should return status 202", 202, output.getStatus());
    }

    @Test
    public void getBalance() throws Exception {
        PowerMock.mockStatic(CustomerProvider.class);
        
    }

    @Test
    public void updateBalance() throws Exception {
        PowerMock.mockStatic(CustomerProvider.class);
    }

}