package db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * ����oracle���ݿ�
 *
 * @author ����
 */
public final class DbConn {
    static final String USER = "root";
    static final String PWD = "root";
    // ���ݿ�����Ϊ��shopping_management
    static final String URL = "jdbc:mysql://@localhost:3306/shopping_management?useSSL=false";

    public static Connection getconn() {
        Connection conn = null;
        //�Ѽ���������
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
