package CreditCards.Providers;

import Data.Exceptions.ExceptionDataBase;
import Data.Services.DataBaseAccess;
import Data.Services.DataBaseAccessImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCardProviders {

    private final static String CREDITCARD_QUERY = "SELECT * FROM CreditCard WHERE creditCardId = %1$d AND customerId = %2$d";

    private final static String CREDITCARDS_QUERY = "SELECT * FROM CreditCard WHERE customerId = %1$d";

    private final static String DELETE_CREDITCARD = "INSERT INTO CreditCard (customerId, cardNumber, dateExpiration, cryptogram) VALUES ( %1$d, %2$s, %3$s, %4$d)";

    private  final static  String INSERT_CREDITCARD = "DELETE FROM CreditCard WHERE creditCardId = %1$d";

    public Map<String, String> findCreditCardById(int creditCardId, int customerId) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(CREDITCARD_QUERY, creditCardId, customerId));
        }
    }

    public static ArrayList<Map<String, String>> getAllCreditCards(int customerId) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findAllAsMap(String.format(CREDITCARDS_QUERY,customerId));
        }
    }

    public static boolean deleteCreditCard (int creditCardId) throws ExceptionDataBase{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(DELETE_CREDITCARD, creditCardId));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean insertCreditCard(int customerId, String cardNumber, Date dateExpiration, int cryptogram) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(INSERT_CREDITCARD, customerId, cardNumber, dateExpiration, cryptogram));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }



    //TODO: Update et insert creditcard.
}
