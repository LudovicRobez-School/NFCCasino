package fr.Customers.Providers;

import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;


// Marc remplace les db.query par db.update lors des insert ou des update. De plus db.update retourne un booleen si il s'est bien executé.
public class CustomerProvider {


    private final static String INFO_CUSTOMER_QUERY = "SELECT * FROM Customer WHERE c_mail =%1$s";

    private final static String UPDATE_FIRSTNAME_QUERY = "UPDATE Customer SET c_firstname = %1$s WHERE c_mail = %2$s";

    private final static String UPDATE_LASTNAME_QUERY = "UPDATE Customer SET c_lastname = %1$s WHERE c_mail = %2$s";

    private final static String UPDATE_PASSWORD_QUERY = "UPDATE Customer SET c_password = %1$s WHERE c_mail = %2$s";

    private final static String UPDATE_BALANCE_QUERY = "UPDATE Customer SET c_balance = %1$f WHERE c_mail = %2$s";

    private final static String GET_BALANCE_QUERY = "SELECT c_balance FROM Customer WHERE c_mail = %1$s"; // J'ai pas changer en Map pasque ca retourne juste 1 seule atribut et c'est plues facile de retourner juste un double

    private final static String INSERT_CUSTOMER = "INSERT INTO Customer(c_mail, c_password, c_firstname, c_lastname, c_address, c_zipcode, c_city, c_state, c_country) VALUES (%2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s)";

    private  final static  String DELETE_CUSTOMER = "DELETE FROM Customer WHERE c_mail = %1$s";

    private  final static  String UPDATE_COUNTRY_QUERY = "UPDATE Customer SET c_country = %1$s WHERE c_mail = %2$s";

    private  final static  String UPDATE_ADRESS_QUERY = "UPDATE Customer SET c_address = %1$s WHERE c_mail = %2$s";

    private  final static  String UPDATE_POSTAL_QUERY = "UPDATE Customer SET c_zipcode = %1$s WHERE c_mail = %2$s";

    private  final static  String UPDATE_STATE_QUERY = "UPDATE Customer SET c_state = %1$s WHERE c_mail = %2$s";

    private  final static  String UPDATE_ALLOFCUSTOMER_QUERY = "UPDATE Customer SET c_country = %1$s, c_address = %2$s, c_postal = %3$s, c_state = %4$s, WHERE c_mail = %5$s";


    public static boolean updateInfoCustomer (String mail, String country, String adress, String postal, String state) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_ALLOFCUSTOMER_QUERY, country, adress, postal, state, mail));
            return true;
        }
    }

    public static boolean changeCountry (String mail, String country) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.update(String.format(UPDATE_COUNTRY_QUERY, country, mail));
            return true;
        }
    }

    public static boolean changeAdress (String mail, String adress) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_ADRESS_QUERY, adress, mail));
            return true;
        }
    }

    public static boolean changePostal (String mail, String postal) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_POSTAL_QUERY, postal, mail));
            return true;
        }
    }
    public static boolean changeState (String mail, String state) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_STATE_QUERY, state, mail));
            return true;
        }
    }

    public static Map<String,String> CustomerInfo (String mail) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(INFO_CUSTOMER_QUERY, mail));
        }
    }

    public static boolean changeFirstName (String mail, String fn) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_FIRSTNAME_QUERY, fn, mail));
            return true;
        }
    }

    public static boolean changeLastName (String mail, String ls) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_LASTNAME_QUERY, ls, mail));
            return true;
        }
    }

    public static boolean changePassword (String mail, String pass) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(UPDATE_PASSWORD_QUERY, pass, mail));
            return true;
        }
    }
    public static double getBalance (String mail) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            ResultSet rs = db.query(String.format(GET_BALANCE_QUERY, mail));
            return rs.getDouble("balance");
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
    }

    //Marc Pas besoin de reouvrir une db car il s'agit de la meme db la db sera automatiquement fermer a la fin
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
    }


    public static boolean deleteCustomer (String mail) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(DELETE_CUSTOMER, mail));
            return true;
        }
    }

    public static boolean insertCustomer(String mail, String firstName, String lastName, String password, String contry, String adress, String postal, String state) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(INSERT_CUSTOMER, mail, firstName, lastName, password, contry, adress, postal, state));
            return true;
        }
    }
}
