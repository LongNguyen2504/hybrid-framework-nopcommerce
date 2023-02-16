package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnUtils {
    public static Connection getSQLServerConnection() {
        String hostName = "localhost";
        String sqlInstanceName = "SQLExpress";
        String database = "automationtest";
        String userName = "sa";
        String password = "123";

        return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
    }

    public static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
        Connection conn = null;
        try {


            // Cấu trúc URL Connection dành cho SQL Server
            String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"   + ";databaseName=" + database + ";user=" + userName + ";password=" + password  + ";encrypt=false;";
/*            String connectionURL = "jdbc:sqlserver://" + hostName + ":61597" + ";instance=" + sqlInstanceName + ";databaseName=" + database + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;" +  "trustStore=storeName;trustStorePassword=storePassword;" +
                    "hostNameInCertificate=hostName";*/
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
