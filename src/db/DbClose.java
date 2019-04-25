package db;

import java.sql.*;

/**
 * �رղ������ݿ�ʱ��������Դ��
 *
 * @author lyons(zhanglei)
 */
public final class DbClose {
    /**
     * �ر� ��ӹ��� ��Դ
     *
     * @param pstmt,rs,conn
     */
    public static void addClose(PreparedStatement pstmt, Connection conn) {
        /*
         * ��� try-catch �����㣺��ȫ
         */
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * �ر���Դ
     *
     * @param pstmt PreparedStatement
     * @param rs    �����
     * @param conn  ����
     */
    public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
