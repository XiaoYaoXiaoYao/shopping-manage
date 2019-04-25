package service;

import dao.GoodsDao;
import entity.Goods;
import entity.Gsales;
import tools.Arith;
import tools.NextTool;

import java.util.ArrayList;

import static page.MainPage.shoppingSettlementPage;
import static tools.InputUtil.*;

public class GoodsService {
    static GoodsDao goodsDao = new GoodsDao();

    public void choiceGoodsById(int salesManSid) {
        System.out.println("--按商品编号选择商品--");
        //传参gid，调用精确查询商品
        int shoppingGid = InputInt();

        ArrayList<Goods> goodsList = goodsDao.queryGoodsKey(shoppingGid, null);
        if (goodsList == null || goodsList.size() == 0) {
            System.err.println("\t！！查无此商品 ！！\n");
        } else {
            Goods goods = goodsList.get(0);
            int gNum = goods.getGnum();
            double gPrice = goods.getGprice();

            System.out.println("--请输入购买数量--");
            do {
                int choiceGoodsNum = InputInt();//获取用户要购买的数量

                if (choiceGoodsNum > gNum) {
                    System.err.println("\t！！仓库储备不足！！");
                    System.out.println("--请重新输入购买数量--");
                    continue;
                }
                double allPrice = Arith.mul(choiceGoodsNum, gPrice);//利用BigDecimal作乘法运算
                System.out.println("\t\t\t  购物车结算\n");
                System.out.println("\t\t商品名称\t商品单价\t购买数量\t总价\n");
                System.out.println("\t\t" + goods.getGname() + "\t" + gPrice + " $\t" + choiceGoodsNum + "\t" + allPrice + " $\n");

                boolean buyRes = true;
                do {
                    System.out.println("确认购买：Y/N");
                    String choShopping = InputString();
                    if ("y".equals(choShopping) || "Y".equals(choShopping)) {
                        System.out.println("\n总价：" + allPrice + " $");
                        System.out.println("\n实际缴费金额");

                        boolean res = true;
                        do {
                            res = GoodsService.buyGoods(salesManSid, goods, gNum, choiceGoodsNum, allPrice);
                        } while (res);
                        buyRes = false;
                    } else if ("N".equals(choShopping) || "n".equals(choShopping)) {
                        shoppingSettlementPage(salesManSid);
                        buyRes = false;
                    }
                    System.err.println("\t！！请确认购物意向！！");
                } while (buyRes);
            } while (true);
        }
    }

    public static boolean buyGoods(int salesManSid, Goods goods, int gNum, int choiceGoodsNum, double allPrice) {
        double amount = InputDouble();
        double balance = Arith.sub(amount, allPrice);  // 用户交钱与购买物品总价间的差额
        if (balance < 0) {
            System.err.println("\t！！缴纳金额不足！！");
            System.out.println("\n请重新输入缴纳金额($)");
            return true;
        }
        /*	这里是购物结算操作数据库！！！！！！
         * 1.更改goods表数量
         * 2.增加sales表数量
         * 原商品数量gNum。 结算员Id  salesManSid
         */

        //对sales表操作
        Gsales gSales = new Gsales(goods.getGid(), salesManSid, choiceGoodsNum);
        boolean insert = new GsalesService().shoppingSettlement(gSales);

        //对goods表操作
        int goodsNewNum = gNum - choiceGoodsNum; //现在goods表中该商品数量
        Goods newGoods = new Goods(goods.getGid(), goodsNewNum);
        boolean update = new GoodsDao().updateGoods(3, newGoods);

        if (update && insert) {
            System.out.println("找零：" + balance);
            System.out.println("\n谢谢光临，欢迎下次惠顾");
        } else {
            System.err.println("！支付失败！"); // 出现这个错误一定是数据库操作有问题！
        }
        shoppingSettlementPage(salesManSid);//最后跳转到到购物结算页面
        //	结束购物结算操作数据库！！！！！！-----------------------------------
        return false;

    }

    public static void updateGoodsNum(int gid) {
        System.out.println("请输入商品-新数量 ");
        int gNum = InputInt();
        Goods goodsNum = new Goods(gid, gNum);
        boolean boolNum = new GoodsDao().updateGoods(3, goodsNum);
        if (boolNum) {
            System.out.println("\n\t！！成功更新商品数量至数据库！！\n");
        } else {
            System.err.println("\n\t！！更新商品数量失敗！！");
        }
    }

    public static void updateGoodsPrice(int gid) {
        System.out.println("请输入商品-新价格 ");
        double gprice = InputDouble();
        Goods goodsPrice = new Goods(gid, gprice);
        boolean boolPrice = new GoodsDao().updateGoods(2, goodsPrice);

        if (boolPrice) {
            System.out.println("\n\t！！成功更新商品价格至数据库！！\n");
        } else {
            System.err.println("\n\t！！更新商品价格失敗！！");
        }
    }

    public static void updateGoodsName(int gid) {
        System.out.println("请输入商品-新名称");
        String gname = InputString();
        Goods goodsName = new Goods(gid, gname);
        boolean boolName = new GoodsDao().updateGoods(1, goodsName);
        if (boolName) {
            System.out.println("\n\t！！成功更新商品名至数据库！！\n");
        } else {
            System.err.println("\n\t！！更新商品名失敗！！");
        }
    }

    public static void deleteGoods(int gid) {
        //进行刪除-数据库操作
        boolean boolDeleteGoods = new GoodsDao().deleteGoods(gid);//調用刪除功能

        if (boolDeleteGoods) {
            System.err.println("\t！！已成功刪除该商品！！\n");
        } else {
            System.err.println("\n\t！！刪除该商品失敗！！");
        }
        NextTool.changedInfoNext("deleteGoodsPage");
    }

    public boolean addGoods(Goods goods) {
        return new GoodsDao().addGoods(goods);
    }

    public ArrayList<Goods> queryGoods(int choice) {
        return new GoodsDao().queryGoods(choice);
    }

    public ArrayList<Goods> displayGoods() {
        return new GoodsDao().displayGoods();
    }
}
