package DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseConnector {

    private SQLServerDataSource dataSource;
    public DatabaseConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("g7_myTunes");
        dataSource.setUser("CSe22A_10");
        dataSource.setPassword("CSe22A_10");
        dataSource.setTrustServerCertificate(true);
        dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnector dbCon = new DatabaseConnector();

        try (Connection connection = dbCon.getConnection()) {
            System.out.println("Is it open?" + !connection.isClosed());
        }
    }
}
