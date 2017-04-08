package fr.CreditCards.Services;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.CreditCards.Ressources.CreditCard;
import fr.Security.RSA.Cryptography;
import junit.framework.TestCase;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Application;
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

    @Test
    public void getAllCreditCards() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.getAllCreditCardsById(anyInt())).andReturn(new ArrayList<Map<String, String>>());

        Response output = target("/creditcard/" + Cryptography.chiffrementRSA("1")).request().get();

        Assert.assertEquals("should return status 200", 200, output.getStatus());

        TestCase.assertNotNull("Should return list", output.getEntity());
    }

    @Test
    public void addCreditCard() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.insertCreditCard(anyInt(), anyString(), anyInt(), anyInt(), anyString())).andReturn(true);

        Response output = target("/creditcard").request().post();

        Assert.assertEquals("should return status 202", 202, output.getStatus());
    }

    @Test
    public void getCreditCard() throws Exception {

        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.findCreditCardById(anyInt(),anyInt())).andReturn(new HashMap<String, String>());

        Response output = target("/creditcard").request().get();

        Assert.assertEquals("should return status 200", 200, output.getStatus());

        TestCase.assertNotNull("Should return list", output.getEntity());
    }

    @Test
    public void deleteCreditCard() throws Exception {
        PowerMock.mockStatic(CreditCardProviders.class);
        expect(CreditCardProviders.deleteCreditCard(anyInt())).andReturn(true);

        Response output = target("/creditcard").request().delete();

        Assert.assertEquals("should return status 200", 200, output.getStatus());
    }

}