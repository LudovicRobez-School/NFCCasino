package fr.CreditCards.Providers;

import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCardProviders {

    private final static String CREDITCARD_QUERY = "SELECT * FROM CreditCard WHERE creditCardId = %1$d AND customerId = %2$d";

    private final static String CREDITCARDS_QUERY = "SELECT * FROM CreditCard WHERE customerId = %1$d";

    private final static String DELETE_CREDITCARD = "INSERT INTO CreditCard (customerId, cardNumber, dateExpiration, cryptogram, type) VALUES ( %1$d, %2$s, %3$s, %4$d, %5$s)";

    private  final static  String INSERT_CREDITCARD = "DELETE FROM CreditCard WHERE creditCardId = %1$d";

    public static Map<String, String> findCreditCardById(int creditCardId, String customerId) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(CREDITCARD_QUERY, creditCardId, customerId));
        }
    }

    public static ArrayList<Map<String, String>> getAllCreditCardsById(String customerId) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findAllAsMap(String.format(CREDITCARDS_QUERY,customerId));
        }
    }

    public static boolean deleteCreditCard (int creditCardId) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.update(String.format(DELETE_CREDITCARD, creditCardId));
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean insertCreditCard(Map<String, String> creditCard) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.update(String.format(INSERT_CREDITCARD, Integer.parseInt(creditCard.get("customerId")), creditCard.get("cardNumber"), Integer.parseInt(creditCard.get("dateExpiration")), Integer.parseInt(creditCard.get("cryptogram")), creditCard.get("type")));
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
