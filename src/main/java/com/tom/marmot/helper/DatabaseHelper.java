package com.tom.marmot.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库操作助手
 *
 * @author : tdl
 * @date : 2019/7/10 下午2:11
 **/
public final class DatabaseHelper {

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        DRIVER = ConfigHelper.getJdbcDriver();
        URL = ConfigHelper.getJdbcUri();
        USERNAME = ConfigHelper.getJdbcUserName();
        PASSWORD = ConfigHelper.getJdbcPassword();

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("找不到JDBC Driver类");
        }
    }

    private static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CONNECTION_HOLDER.set(conn);
        }

        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
