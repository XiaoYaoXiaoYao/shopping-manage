package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.Goods;
import entity.Gsales;

/**
 * 数据库gSales表操作
 *
 * @author lyons(zhanglei), 过道
 */
public final class GsalesDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 2.购物结算-向sales表中插入商品数据！
     *
     * @param gSales 售卖商品对象
     * @return boolean
     */
    public boolean shoppingSettlement(Gsales gSales) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO gsales(gid,sid,snum) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gSales.getGId());
            pstmt.setInt(2, gSales.getSId());
            pstmt.setInt(3, gSales.getSNum());

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

}
