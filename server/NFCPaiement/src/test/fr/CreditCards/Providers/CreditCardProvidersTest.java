package fr.CreditCards.Providers;

import org.junit.Before;
import org.junit.Test;
import fr.Data.Services.DataBaseAccessImpl;
import fr.Data.Services.DataBaseAccess;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.easymock.EasyMock.*;

/**
 * Created by ludov on 25/02/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CreditCardProviders.class, DataBaseAccess.class, DataBaseAccessImpl.class })
public class CreditCardProvidersTest {

    //region PROPS
    DataBaseAccess dbMock;
    //endregion

    //region CONFIG
    public Application configure() {

        return new ResourceConfig(CreditCardProviders.class);
    }

    @Before
    public void setUp() throws Exception {
        dbMock = createNiceMock(DataBaseAccess.class);

        PowerMock.mockStatic(DataBaseAccessImpl.class);
        expect(DataBaseAccessImpl.getDbConnection()).andReturn(dbMock);
    }

    @Test
    public void findCreditCardById() throws Exception {
        Map<String, String> creditCard = new HashMap<String, String>();
        creditCard.put("test", "test");
        expect(dbMock.findOneAsMap(anyString())).andReturn(creditCard);
        PowerMock.replayAll(dbMock);
        Assert.assertEquals("should return the map", creditCard, CreditCardProviders.findCreditCardById(1,1));

        PowerMock.verifyAll();
    }

    @Test
    public void getAllCreditCardsById() throws Exception {

        ArrayList<Map<String, String>> creditCards = new  ArrayList<Map<String, String>>(){{
            new HashMap<String, String>(){{
                put("test", "test");
            }};

            new HashMap<String, String>(){{
                put("test", "test");
            }};

        }};

        expect(dbMock.findAllAsMap(anyString())).andReturn(creditCards);
        PowerMock.replayAll(dbMock);
        Assert.assertEquals("should return the list", creditCards, CreditCardProviders.getAllCreditCardsById(1));
        PowerMock.verifyAll();
    }

    @Test
    public void deleteCreditCard() throws Exception {
        expect(dbMock.query(anyString()));
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true", CreditCardProviders.deleteCreditCard(1));
        PowerMock.verifyAll();
    }

    @Test
    public void insertCreditCard() throws Exception {
        expect(dbMock.query(anyString()));
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true", CreditCardProviders.insertCreditCard(1,"test", 0000, 000, "VISA"));
        PowerMock.verifyAll();
    }

}