package fr.Customers.Providers;

import fr.Customers.Ressources.Customer;
import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class CustomerProvider {


    private final static String SELECTALL_CUSTOMER_QUERY = "SELECT * FROM Customer WHERE mail =%1$s";

    private final static String UPDATE_FIRSTNAME_QUERY = "UPDATE Customer SET firstName = %1$s WHERE mail = %2$s";

    private final static String UPDATE_LASTNAME_QUERY = "UPDATE Customer SET lastName = %1$s WHERE mail = %2$s";

    private final static String UPDATE_PASSWORD_QUERY = "UPDATE Customer SET password = %1$s WHERE mail = %2$s";

    private final static String UPDATE_BALANCE_QUERY = "UPDATE Customer SET balance = %1$d WHERE mail = %2$s";

    private final static String GET_BALANCE_QUERY = "SELECT balance FROM Customer WHERE mail = %1$s";

    private final static String INSERT_CUSTOMER = "INSERT INTO Customer (mail, firstName, lastName, password) VALUES ( %1$s, %2$s, %3$s, %4$s)";

    private  final static  String DELETE_CUSTOMER = "DELETE FROM Customer WHERE mail = %1$s";

    /*public Map<String, String> findCreditCardById(int creditCardId, int customerId) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(CREDITCARD_QUERY, creditCardId, customerId));
        }
    }

    public static ArrayList<Map<String, String>> getAllCreditCardsById(int customerId) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findAllAsMap(String.format(CREDITCARDS_QUERY,customerId));
        }
    }*/

    public static ArrayList delBalance (String mail) throws Exception{
        ArrayList a = new ArrayList();
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            ResultSet rs=db.query(String.format(SELECTALL_CUSTOMER_QUERY, mail));

            while(rs.next()){
                a.add(rs.getString(1));
                a.add(rs.getString(2));
                a.add(rs.getString(3));
                a.add(rs.getString(4));
                a.add(rs.getDouble(5));
            }
            return a;

        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean changeFirstName (String mail, String fn) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_FIRSTNAME_QUERY, fn, mail));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean changeLastName (String mail, String ls) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_LASTNAME_QUERY, ls, mail));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean changePassword (String mail, String pass) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_PASSWORD_QUERY, pass, mail));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean delBalance (String mail, double balance) throws Exception{
        double b;
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            ResultSet rs = db.query(String.format(GET_BALANCE_QUERY, mail));
            b = rs.getDouble("balance");

            b -= balance;
            try (DataBaseAccess db1 = DataBaseAccessImpl.getDbConnection()) {
                db1.query(String.format(UPDATE_BALANCE_QUERY, b, mail));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean addBalance (String mail, double balance) throws Exception{
        double b;
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            ResultSet rs=db.query(String.format(GET_BALANCE_QUERY, mail));
            b=rs.getDouble("balance");

            b+=balance;
            try (DataBaseAccess db1 = DataBaseAccessImpl.getDbConnection()) {
                db1.query(String.format(UPDATE_BALANCE_QUERY, b,mail));
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public static boolean deleteCustomer (String mail) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(DELETE_CUSTOMER, mail));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean insertCustomer(String mail, String firstName, String lastName, String password) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(INSERT_CUSTOMER, mail, firstName, lastName, password));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
