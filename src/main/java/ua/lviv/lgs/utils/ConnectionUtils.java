package ua.lviv.lgs.utils;

import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static String USER_NAME="vitalii";
    private static String USER_PASSWORD="123486";
    private static String URL="jdbc:mysql://localhost/shop_mag";
    public static Connection openConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DOMConfigurator.configure("LoggerConfig.xml");
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD);
    }
}

