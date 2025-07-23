package project.integration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerTest {
    @Test
    void testDatabaseConnection() {
        try (PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:15")) {
            db.start();

            String jdbcUrl = db.getJdbcUrl();
            String username = db.getUsername();
            String password = db.getPassword();
            System.out.println("JDBC: " + db.getJdbcUrl());
        }
    }
}
