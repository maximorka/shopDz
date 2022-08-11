package productShop.storage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage {
    public static Storage getINSTANCE() {
        return INSTANCE;
    }

    public static final String connectionURL = "jdbc:h2:./shopDB";
    public static final Storage INSTANCE = new Storage();
    private Connection connection;


    private Storage() {
        try {
            connection = DriverManager.getConnection(connectionURL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
