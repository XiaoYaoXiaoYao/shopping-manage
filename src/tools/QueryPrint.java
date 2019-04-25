package tools;

import java.util.ArrayList;

import dao.GoodsDao;
import entity.Goods;

import static tools.NextTool.changedInfoNext;
import static tools.PrintMenu.printGoodsNumInfo;

/**
 * 查询&&打印 函数工具(后期优化可能会删)
 *
 * @author lyons(zhanglei)
 */
public final class QueryPrint {

    /**
     * 模糊查询并陈列查询信息函数小工具
     *
     * @param oper 调用者
     * @return 查询到的信息的gid, 如果返回值等于-1，则代表查询异常。
     */
    public static int query(String oper) {
        int gid = -1;
        String shopping = InputUtil.InputString(); //键盘获取商品名字
        ArrayList<Goods> goodsList = new GoodsDao().queryGoodsKey(-1, shopping);  //根据键盘获取的商品名字調用 精确查询函数，確定用戶所要操作的数据
        if (goodsList == null || goodsList.size() <= 0) {
            System.err.println("\t！！查无此商品 ！！");

            //調用选择下一步函数
            changedInfoNext(oper);

        } else { //查到有此商品，实现进行 更改商品 信息操作！
            Goods goods = goodsList.get(0);

            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
            printGoodsNumInfo(goods);

            gid = goods.getGid(); //将商品编号返回给调用者
        }
        return gid;
    }

    /**
     * 模糊查询函数小工具
     *
     * @return int 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
     */
    public static int querySettlement() {
        int gid = -1;
        ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);//調用 关键字查询函数
        if (goodsSettlement == null || goodsSettlement.size() <= 0) {
            System.err.println("\t！！查无此商品 ！！\n");
            gid = -3;
        } else {  //查到有此商品，实现进行 更改商品 信息操作！
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0; i < goodsSettlement.size(); i++) {
                Goods goods = goodsSettlement.get(i);
                if (goods.getGnum() > 0) {
                    System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());

                    printGoodsNumInfo(goods);

                    if (goodsSettlement.size() == 1) {
                        gid = goods.getGid(); //将商品编号返回给调用者
                    } else {
                        gid = -2;
                    }
                }
            }
        }
        return gid;
    }
}
