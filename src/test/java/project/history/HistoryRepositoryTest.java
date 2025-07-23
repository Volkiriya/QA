package project.history;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HistoryRepositoryTest {

    @Container
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:15");

    private DataSource ds;
    private HistoryRepository repo;

    @BeforeAll
    void init() throws Exception {
        Class.forName("org.postgresql.Driver");

        ds = new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(
                        db.getJdbcUrl(),
                        db.getUsername(),
                        db.getPassword());
            }
            @Override
            public Connection getConnection(String u, String p) throws SQLException {
                return DriverManager.getConnection(db.getJdbcUrl(), u, p);
            }

            /* остальные методы можно оставить заглушками */
            public <T> T unwrap(Class<T> iface) throws SQLException { throw new SQLException(); }
            public boolean isWrapperFor(Class<?> iface){ return false; }
            public PrintWriter getLogWriter(){ return null; }
            public void setLogWriter(PrintWriter out){}
            public void setLoginTimeout(int seconds){}
            public int getLoginTimeout(){ return 0; }
            public Logger getParentLogger(){ return Logger.getGlobal(); }
        };

        try (var c = ds.getConnection(); var st = c.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS operations (
                      id      SERIAL PRIMARY KEY,
                      a       INT,
                      b       INT,
                      op      VARCHAR(10),
                      result  DOUBLE PRECISION
                )
            """);
        }
        repo = new HistoryRepository(ds);
    }

    @Test
    void shouldSaveOperation() throws Exception {
        repo.save(new Operation(2, 3, "+", 5.0));

        try (var c = ds.getConnection();
             var st = c.createStatement();
             var rs = st.executeQuery("SELECT a,b,op,result FROM operations")) {

            assertTrue(rs.next(), "строка не найдена");
            assertEquals(2,     rs.getInt(1));
            assertEquals(3,     rs.getInt(2));
            assertEquals("+",   rs.getString(3));
            assertEquals(5.0,   rs.getDouble(4), 0.0001);
            assertFalse(rs.next(), "лишние строки");
        }
    }
}
