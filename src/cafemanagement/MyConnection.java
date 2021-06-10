package cafemanagement;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnection {
    public static Connection getConnection()
    {
        Connection con =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projectmanagement?zeroDateTimeBehavior=convertToNull","root","");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return con;
        
    }
}
