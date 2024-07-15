package util;

import com.revature.cra.util.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionFactoryTestSuite {

    @Test
    public void test_getConnection_returnValidConnection(){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            assertNotNull(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
