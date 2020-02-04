package control;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.ds.PGConnectionPoolDataSource;

//public class ConnectionFactory {
//
//    public Connection getConnection(String dir, String user, String password) throws ClassNotFoundException, SQLException, IOException {
//        //jdbc:postgresql://localhost:5432/example", "postgres", "postgres"
//        //usar Connection connection = DriverManager
//        return DriverManager.getConnection("jdbc:postgresql:" + dir, user, password);
//    }
//
//}
public class ConnectionFactory {

    private static Connection connection;
    private static javax.sql.ConnectionPoolDataSource dataSource;

    private static void createConnectionPool(String dir, String user, String password) {

        PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();
        pool.setUrl("jdbc:postgresql:"+dir);
        pool.setUser(user);
        pool.setPassword(password);
        dataSource = pool;
    }
    
    //era public static connection
    public Connection getConnection(String dir, String user, String password) throws SQLException {
        if (dataSource == null) {
            createConnectionPool(dir, user, password);
        }
        if (connection == null || connection.isClosed()) {
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost/alura", "postgres", "postgres");            
            connection = dataSource.getPooledConnection().getConnection();
            //connection.setAutoCommit(false);
        }
        return connection;

    }
}
