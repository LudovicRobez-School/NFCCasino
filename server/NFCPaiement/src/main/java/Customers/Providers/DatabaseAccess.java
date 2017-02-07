package Customers.Providers;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ResourceBundle;

import static javax.swing.UIManager.getString;

/**
 * Created by Greg on 31/01/2017.
 */

public class DatabaseAccess {
    private Connection currentConnection = null;
    private Statement statement = null;
    private static DatabaseAccess database;
    private ResultSet result = null;

    private DatabaseAccess() {
        // Get informations for database with configuration file
        try {
            InitialContext ic = new InitialContext();
            DataSource databaseSource = (DataSource)ic.lookup("java:comp/env/jdbc/CommonDB"); // A modifier
            this.currentConnection = databaseSource.getConnection();
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static synchronized DatabaseAccess getDbConnection() {
        if(database == null) {
            database = new DatabaseAccess();
        }

        return database;
    }

    public ResultSet query(String query) throws SQLException {
        statement = currentConnection.createStatement();
        result = statement.executeQuery(query);
        return result;
    }

    public ResultSet concurrentQuery(String query) throws SQLException {
        statement = currentConnection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        result = statement.executeQuery(query);
        return result;
    }

    public void update(String query) throws SQLException {
        statement = currentConnection.createStatement();
        statement.executeUpdate(query);
    }


    public void closeConnection() throws SQLException {
        try {
            currentConnection.close();
            database = null;
        } catch (SQLException e) {
            throw e;
        }
    }
}