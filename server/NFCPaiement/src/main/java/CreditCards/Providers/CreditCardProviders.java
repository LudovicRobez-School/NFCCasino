package CreditCards.Providers;

import Data.DataBaseAccess;
import Data.DataBaseAccessImpl;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCardProviders {

    private final static String CREDITCARD_QUERY = "SELECT * FROM CreditCard WHERE creditCardId = %1$d";

    private final static String CREDITCARDS_QUERY = "SELECT * FROM CreditCard";

    private final static String DELETE_CREDITCARD = "";  //TODO

    private  final static  String INSERT_CREDITCARD = ""; //TODO

    public Map<String, String> findCreditCardById(int id) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(CREDITCARD_QUERY, id));
        }
    }

    public static ArrayList<Map<String, String>> getAllCreditCards() throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findAllAsMap(CREDITCARDS_QUERY);
        }
    }

    //TODO: Update et insert creditcard.
}
