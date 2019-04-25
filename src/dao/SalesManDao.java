package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.SalesMan;

/**
 * ���ݿ�SalesMan�����
 *
 * @author lyons(zhanglei), ����
 */
public final class SalesManDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.ǰ̨������½
     *
     * @param sName �û���
     * @return ArrayList<SalesMan> sPassWord,sId
     */
    public ArrayList<SalesMan> checkstandLog(String sName) {
        ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
        conn = DbConn.getconn();
        String sql = "SELECT sid,spassword FROM salesman WHERE sname=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String sPassWord = rs.getString("spassword");
                int sId = rs.getInt("sId");
                SalesMan salesMan = new SalesMan(sId, sPassWord);
                salesManInfo.add(salesMan);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManInfo;
    }

    /**
     * 2.����ۻ�Ա
     *
     * @param sName �û���
     * @return boolean
     */
    public boolean addSalesMan(SalesMan sName) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO salesman(sname,spassword) VALUES(?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName.getSName());
            pstmt.setString(2, sName.getSPassWord());

            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 3.�����ۻ�Ա��Ϣ
     *
     * @param key   Ҫ������
     * @param sName �û���
     * @return boolean
     */
    public boolean updateSalesMan(int key, SalesMan sName) {
        conn = DbConn.getconn();
        String updateNameSql = "UPDATE salesman SET sname=? WHERE sid=?";
        String updatePwdSql = "UPDATE salesman SET spassword=? WHERE sid=?";
        String[] updateSalesmanSql = {updateNameSql, updatePwdSql};
        //	3.1 �����ۻ�Ա����
        try {
            pstmt = conn.prepareStatement(updateSalesmanSql[key - 1]);
            switch (key) {
                case 1:
                    pstmt.setString(1, sName.getSName());
                    break;
                case 2:
                    pstmt.setString(1, sName.getSPassWord());
                    break;
                default:
                    break;
            }
            pstmt.setInt(2, sName.getSId());
            int rs = pstmt.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return false;
    }


    /**
     * 4.ɾ���ۻ�Ա
     *
     * @param sName �û���
     * @return boolean
     */
    public boolean deleteSalesMan(String sName) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "DELETE FROM salesman WHERE sname=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            int res = pstmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }


    /**
     * 5.ģ����ѯ�ۻ�Ա
     *
     * @param sName �û���
     * @return ArrayList<SalesMan>
     */
    public ArrayList<SalesMan> querySalesMan(String sName) {
        ArrayList<SalesMan> SalesManList = new ArrayList<>();
        conn = DbConn.getconn();

        sName = "%" + sName + "%";    //���û�����ȡ���ַ������� % ���ţ����ﵽģ����ѯ��Ŀ��.�ַ��� �����ӻ��и�����ķ�ʽ�����Ż����룡
        String sql = "SELECT sid,sname,spassword FROM salesman WHERE sname LIKE ?";  //��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            getResList(SalesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return SalesManList;
    }

    void getResList(ArrayList<SalesMan> salesManList) throws SQLException {
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int sid = rs.getInt(1);
            String sname = rs.getString(2);
            String sPassWord = rs.getString(3);
            SalesMan salesMan = new SalesMan(sid, sname, sPassWord);
            salesManList.add(salesMan);
        }
    }

    /**
     * 6.��ʾ�����ۻ�Ա
     *
     * @return ArrayList<SalesMan>
     */
    public ArrayList<SalesMan> displaySalesMan() {
        ArrayList<SalesMan> salesManList = new ArrayList<>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM salesman";
        try {
            pstmt = conn.prepareStatement(sql);
            getResList(salesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManList;
    }
}
