package fr.Tokens.Providers;

import com.paypal.api.payments.Payment;
import fr.CreditCards.Ressources.CreditCard;
import fr.Customers.Ressources.Customer;
import fr.Tokens.Ressources.Token;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Application;

import static org.easymock.EasyMock.createNiceMock;

/**
 * Created by ludov on 12/05/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TokenProcess.class, Token.class, Customer.class, CreditCard.class, Payment.class})
public class TokenProcessTest {

    //region PROPS
    Token token;
    Customer customer;
    CreditCard creditCard;
    Payment payment;
    //endregion

    //region CONFIG
    public Application configure() {

        return new ResourceConfig(TokenProcess.class);
    }

    @Before
    public void setUp() throws Exception {
        token = createNiceMock(Token.class);
        customer = createNiceMock(Customer.class);
        creditCard = createNiceMock(CreditCard.class);
        payment = createNiceMock(Payment.class);
    }

    @Test
    public void processPaiement() throws Exception {
        Assert.assertEquals("Should return payement", true ,TokenProcess.processPaiement(payment));
    }

    @Test
    public void initPaiement() throws Exception {
        Assert.assertEquals("Should return payement", new Payment() ,TokenProcess.initPaiement(creditCard,customer,token));
    }

    @Test
    public void generateToken() throws Exception {
        Assert.assertNotNull("Should return String", TokenProcess.generateToken());
    }

}