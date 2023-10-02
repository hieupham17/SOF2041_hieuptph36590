package com.edusys.utils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;

public class JDBCHelper {

    public static Connection getconConnection() {
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setUser("sa");
        dataSource.setDatabaseName("EduSys");
        dataSource.setPassword("1711");
        dataSource.setPortNumber(1433);
        dataSource.setEncrypt(false);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        // Connection conn = DriverManager.getConnection(url, user, pass);
        Connection conn = JDBCHelper.getconConnection();
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);

        }
        return stmt;
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(1);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
