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
 * 数据库goods表操作
 *
 * @author lyons(zhanglei), 过道
 */
public final class GoodsDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.添加商品到数据库goods表
     *
     * @param goods 商品对象
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
     * 2.更改商品信息到数据库goods表
     *
     * @param key   选择要更改商品信息
     * @param goods 商品对象
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
     * 3.从数据库goods表中-刪除商品
     *
     * @param gid 商品编号
     * @return boolean 操作是否成功
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
     * 4.查询商品信息
     *
     * @param key 查询方式
     * @return ArrayList<Goods> 结果集
     */
    public ArrayList<Goods> queryGoods(int key) {
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getconn();

        switch (key) {
            case 1:
                //	key=1商品 数量 升序查询
                String sqlGnum = "SELECT * FROM goods ORDER BY GNUM ASC";
                selectResList(goodsList, sqlGnum);
                break;
            case 2:
                //	key=2商品 价格 升序查询
                String sqlGprice = "SELECT * FROM goods ORDER BY GPRICE ASC";
                selectResList(goodsList, sqlGprice);
                break;
            case 3:
                //	key=3商品 关键字 查询
                selectGoodsByName(goodsList);
                break;
            default:
                break;
        }
        return goodsList;
    }

    /**
     * 根据商品名模糊查询商品集
     *
     * @param goodsList 结果集
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
     * 根据商品 gid or gName 查询商品
     *
     * @param gId   商品,
     * @param gName 商品名称
     * @return 商品信息
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
     * 5.显示所有商品信息
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
     * 执行传入的查询sql语句，并将结果集放入至goodsList中.
     *
     * @param goodsList 结果集
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
     * 这个方法完成重复的从结果集中获取商品数组
     * 重复代码提取,以便于减少代码的数量。
     *
     * @param goodsList 结果集
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