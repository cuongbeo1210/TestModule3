package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://"+serverName+":"+portNumber + "\\" + instance +"/"+dbName;
        if(instance == null || instance.trim().isEmpty())
            url = "jdbc:mysql://"+serverName+":"+portNumber +"/"+dbName;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, userID, password);
    }
    private final String serverName = "localhost";
    private final String dbName = "testmodule3";
    private final String portNumber = "3306";
    private final String instance="";
    private final String userID = "root";
    private final String password = "Cuongdeptrai,,,";
}
