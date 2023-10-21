package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

    public static Connection getConnection(){
        final String driver = "oracle.jdbc.driver.OracleDriver";
        Properties properties = new Properties();
        Connection con = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);

            String url = properties.getProperty("url");
            String name = properties.getProperty("username");
            String passwd = properties.getProperty("passwd");
            Class.forName(driver);
            con = DriverManager.getConnection(url,name,passwd);
            System.out.println("적재성공");


        } catch (Exception e) {
            System.out.println("실패");
        }
        return con;


    }
}
