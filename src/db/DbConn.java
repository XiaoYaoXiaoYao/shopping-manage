package db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * 连接oracle数据库
 *
 * @author 过道
 */
public final class DbConn {
    static final String USER = "root";
    static final String PWD = "root";
    // 数据库名称为：shopping_management
    static final String URL = "jdbc:mysql://@localhost:3306/shopping_management?useSSL=false";

    public static Connection getconn() {
        Connection conn = null;
        //已加载完驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
