package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.Goods;
import tools.InputUtil;

/**
 * ���ݿ�goods�����
 *
 * @author lyons(zhanglei), ����
 */
public final class GoodsDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.�����Ʒ�����ݿ�goods��
     *
     * @param goods ��Ʒ����
     * @return boolean
     */
    public boolean addGoods(Goods goods) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO goods(gname,gprice,gnum) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGname());
            pstmt.setDouble(2, goods.getGprice());
            pstmt.setInt(3, goods.getGnum());

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
     * 2.������Ʒ��Ϣ�����ݿ�goods��
     *
     * @param key   ѡ��Ҫ������Ʒ��Ϣ
     * @param goods ��Ʒ����
     * @return boolean
     */
    public boolean updateGoods(int key, Goods goods) {
        conn = DbConn.getconn();
        String updateNameSql = "UPDATE goods SET gname=? WHERE gid=?";
        String updatePriceSql = "UPDATE goods SET gprice=? WHERE gid=?";
        String updateNumSql = "UPDATE goods SET gnum=? WHERE gid=?";
        String[] sqls = {updateNameSql, updatePriceSql, updateNumSql};
        int rs = 0;
        try {
            pstmt = conn.prepareStatement(sqls[key - 1]);
            switch (key) {
                case 1:
                    pstmt.setString(1, goods.getGname());
                    break;
                case 2:
                    pstmt.setDouble(1, goods.getGprice());
                    break;
                case 3:
                    pstmt.setInt(1, goods.getGnum());
                    break;
                default:
                    break;
            }
            pstmt.setInt(2, goods.getGid());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return rs > 0;
    }

    /**
     * 3.�����ݿ�goods����-�h����Ʒ
     *
     * @param gid ��Ʒ���
     * @return boolean �����Ƿ�ɹ�
     */
    public boolean deleteGoods(int gid) {
        boolean res = false;
        conn = DbConn.getconn();
        String sql = "DELETE FROM goods WHERE gid=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gid);
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return res;
    }

    /**
     * 4.��ѯ��Ʒ��Ϣ
     *
     * @param key ��ѯ��ʽ
     * @return ArrayList<Goods> �����
     */
    public ArrayList<Goods> queryGoods(int key) {
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getconn();

        switch (key) {
            case 1:
                //	key=1��Ʒ ���� �����ѯ
                String sqlGnum = "SELECT * FROM goods ORDER BY GNUM ASC";
                selectResList(goodsList, sqlGnum);
                break;
            case 2:
                //	key=2��Ʒ �۸� �����ѯ
                String sqlGprice = "SELECT * FROM goods ORDER BY GPRICE ASC";
                selectResList(goodsList, sqlGprice);
                break;
            case 3:
                //	key=3��Ʒ �ؼ��� ��ѯ
                selectGoodsByName(goodsList);
                break;
            default:
                break;
        }
        return goodsList;
    }

    /**
     * ������Ʒ��ģ����ѯ��Ʒ��
     *
     * @param goodsList �����
     */
    private void selectGoodsByName(ArrayList<Goods> goodsList) {
        String nameGet = InputUtil.InputString();
        String sqlGname = "SELECT * FROM goods WHERE gname LIKE '%'||?||'%'";
        try {
            pstmt = conn.prepareStatement(sqlGname);
            pstmt.setString(1, nameGet);
            getSelectResList(goodsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
    }

    /**
     * ������Ʒ gid or gName ��ѯ��Ʒ
     *
     * @param gId   ��Ʒ,
     * @param gName ��Ʒ����
     * @return ��Ʒ��Ϣ
     */
    public ArrayList<Goods> queryGoodsKey(int gId, String gName) {
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getconn();

        String sql = "SELECT * FROM goods WHERE gid=? OR gname=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gId);
            pstmt.setString(2, gName);
            getSelectResList(goodsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }

    /**
     * 5.��ʾ������Ʒ��Ϣ
     *
     * @return ArrayList<Goods>
     */
    public ArrayList<Goods> displayGoods() {
        ArrayList<Goods> goodsList = new ArrayList<>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM goods";
        selectResList(goodsList, sql);
        return goodsList;
    }

    /**
     * ִ�д���Ĳ�ѯsql��䣬���������������goodsList��.
     *
     * @param goodsList �����
     * @throws SQLException
     */
    private void selectResList(ArrayList<Goods> goodsList, String sqlGprice) {
        try {
            pstmt = conn.prepareStatement(sqlGprice);
            getSelectResList(goodsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
    }

    /**
     * �����������ظ��Ĵӽ�����л�ȡ��Ʒ����
     * �ظ�������ȡ,�Ա��ڼ��ٴ����������
     *
     * @param goodsList �����
     * @throws SQLException
     */
    void getSelectResList(ArrayList<Goods> goodsList) throws SQLException {
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int gid = rs.getInt(1);
            String gname = rs.getString(2);
            double gprice = rs.getDouble(3);
            int gnum = rs.getInt(4);

            Goods goods = new Goods(gid, gname, gprice, gnum);
            goodsList.add(goods);
        }
    }
}