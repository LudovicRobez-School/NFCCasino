package fr.Customers.Providers;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

/**
 * Created by ludov on 12/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({  CustomerProvider.class, DataBaseAccess.class, DataBaseAccessImpl.class})
public class CustomerProviderTest {

    //region PROPS
    DataBaseAccess dbMock;
    //endregion

    //region CONFIG
    public Application configure() {

        return new ResourceConfig(CustomerProvider.class);
    }

    @Before
    public void setUp() throws Exception {
        dbMock = createNiceMock(DataBaseAccess.class);

        PowerMock.mockStatic(DataBaseAccessImpl.class);
        expect(DataBaseAccessImpl.getDbConnection()).andReturn(dbMock);
    }

    @Test
    public void findCustomerById() throws Exception {
        Map<String, String> customer = new HashMap<String, String>();
        customer.put("test", "test");
        expect(dbMock.findOneAsMap(anyString())).andReturn(customer);
        PowerMock.replayAll(dbMock);
        Assert.assertEquals("should return the map", customer, CustomerProvider.findCustomerById("test"));
        PowerMock.verifyAll();
    }

    @Test
    public void updateCustomer() throws Exception {
        Map<String, String> customer = new HashMap<String, String>();
        customer.put("test", "test");
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true",CustomerProvider.updateCustomer("test",customer));
        PowerMock.verifyAll();
    }

    @Test
    public void deleteCustomer() throws Exception {
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true",CustomerProvider.deleteCustomer("test"));
        PowerMock.verifyAll();
    }

    @Test
    public void insertCustomer() throws Exception {
        Map<String, String> customer = new HashMap<String, String>();
        customer.put("test", "test");
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true", CustomerProvider.insertCustomer(customer));
        PowerMock.verifyAll();
    }

    @Test
    public void findBanlanceById() throws Exception {
        Map<String, String> customer = new HashMap<String, String>();
        customer.put("test", "test");
        expect(dbMock.findOneAsMap(anyString())).andReturn(customer);
        PowerMock.replayAll(dbMock);
        Assert.assertEquals("should return the map", customer, CustomerProvider.findBanlanceById("test"));
        PowerMock.verifyAll();
    }

    @Test
    public void updateBalance() throws Exception {
        Map<String, String> balance = new HashMap<String, String>();
        balance.put("test", "test");
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true",CustomerProvider.updateBalance(balance));
        PowerMock.verifyAll();
    }

}