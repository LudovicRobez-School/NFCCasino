package fr.Tokens.Providers;

import fr.Customers.Providers.CustomerProvider;
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

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;

/**
 * Created by ludov on 12/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({  TokensProvider.class, DataBaseAccess.class, DataBaseAccessImpl.class})
public class TokensProviderTest {

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
    public void findTokenById() throws Exception {
        Map<String, String> token = new HashMap<String, String>();
        token.put("test", "test");
        expect(dbMock.findOneAsMap(anyString())).andReturn(token);
        PowerMock.replayAll(dbMock);
        Assert.assertEquals("should return the map", token, TokensProvider.findTokenById("test"));
        PowerMock.verifyAll();
    }

    @Test
    public void deleteToken() throws Exception {
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true",TokensProvider.deleteToken("test"));
        PowerMock.verifyAll();
    }

    @Test
    public void insertToken() throws Exception {
        Map<String, String> token = new HashMap<String, String>();
        token.put("test", "test");
        expect(dbMock.update(anyString())).andReturn(true);
        PowerMock.replayAll(dbMock);
        Assert.assertTrue("should return true", TokensProvider.insertToken(token));
        PowerMock.verifyAll();
    }

}