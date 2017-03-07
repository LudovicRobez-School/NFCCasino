package fr.Tokens.Providers;

/**
 * Created by rl613611 on 28/02/2017.
 */

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.text.Format;
import java.util.List;
import java.util.ArrayList;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;

import fr.Customers.Ressources.Customer;

import static com.oracle.jrockit.jfr.ContentType.Address;

public class PaiementProcess {

    // Replace with your application client ID and secret
    String clientId = "YOUR APPLICATION CLIENT ID";
    String clientSecret = "YOUR APPLICATION CLIENT SECRET";

    APIContext context = new APIContext(clientId, clientSecret, "sandbox");

    public boolean processPaiement(Payment payment){
        try {
            // Create payment
          Payment createdPayment = payment.create(context);
            System.out.println("Created payment with id = " + createdPayment.getId());
            return true;
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            return false;
        }
    }

    public Payment initPaiement(fr.CreditCards.Ressources.CreditCard myCreditCard, Customer customer, int somme){
        // Set address info
        Address billingAddress = new Address();
        billingAddress.setCity(customer.getCity());
        billingAddress.setCountryCode(customer.getCountry());
        billingAddress.setLine1(customer.getAddress());
        billingAddress.setPostalCode(customer.getPostalCode());
        billingAddress.setState(customer.getState());

// Credit card info
        CreditCard creditCard = new CreditCard();
        creditCard.setBillingAddress(billingAddress);
        creditCard.setExpireMonth(myCreditCard.getDateExpiration()); // t'es vraiment gay ici faut mettre juste le mois (pas en Date)
        creditCard.setExpireYear(myCreditCard.getCryptogram());// et ici c'est l'année, pas le cryptogramme fdp
        creditCard.setFirstName(customer.getFirstName());
        creditCard.setLastName(customer.getLastName());
        creditCard.setNumber(myCreditCard.getCardNumber());
        creditCard.setType(myCreditCard.getType());

// Payment details
        Details details = new Details();
        details.setShipping("1");
        details.setSubtotal("5");
        details.setTax("1");

// Total amount
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(Integer.toString(somme));
        amount.setDetails(details);

// Transaction details
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction
                .setDescription("This is the payment transaction description.");

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

// Set funding instrument
        FundingInstrument fundingInstrument = new FundingInstrument();
        fundingInstrument.setCreditCard(creditCard);

        ArrayList<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
        fundingInstrumentList.add(fundingInstrument);

// Set payer details
        Payer payer = new Payer();
        payer.setFundingInstruments(fundingInstrumentList);
        payer.setPaymentMethod("credit_card");
        return new Payment();
    }
}
