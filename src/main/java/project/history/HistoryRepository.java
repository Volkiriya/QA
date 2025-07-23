package project.history;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoryRepository {

    private final DataSource ds;

    public HistoryRepository(DataSource ds) {
        this.ds = ds;
    }

    public void save(Operation op) throws SQLException {
        String sql = "INSERT INTO operations (a, b, op, result) VALUES (?, ?, ?, ?)";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt   (1, op.a());
            ps.setInt   (2, op.b());
            ps.setString(3, op.op());
            ps.setDouble(4, op.result());
            ps.executeUpdate();
        }
    }
}
