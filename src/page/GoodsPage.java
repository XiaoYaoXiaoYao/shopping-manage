package page;

import java.util.ArrayList;

import entity.Goods;
import tools.PrintMenu;
import service.GoodsService;
import tools.QueryPrint;

import static tools.NextTool.*;
import static tools.InputUtil.*;

/**
 * 操作商品界面
 *
 * @author lyons(zhanglei)
 */

public final class GoodsPage {
    /**
     * 1.添加商品界面
     */
    public static void addGoodsPage() {
        System.out.println("\t--------------正在执行添加商品操作-----------------\n");

        System.out.println("\n請輸入添加商品-名称");
        String goodsName = InputString();

        System.out.println("\n請輸入添加商品-价格");
        double goodsPrice = InputDouble();

        System.out.println("\n請輸入添加商品-数量");
        int goodsNumber = InputInt();

        Goods goods = new Goods(goodsName, goodsPrice, goodsNumber);
        boolean addRes = new GoodsService().addGoods(goods);

        if (addRes) {
            System.out.println("\n\t!您已成功添加商品到数据库!");
        } else {
            System.out.println("---------添加商品失败----------");
        }
        changedInfoNext("addGoodsPage");//选择下一步
    }

    /**
     * 2.更改商品界面
     */
    public static void updateGoodsPage() {
        System.out.println("\t正在执行 更改商品 操作\n");
        System.out.println("请输入想要更改的商品名字");

        //调用查找商品函数，显示将要更改的商品信息
        int gid = QueryPrint.query("updateGoodsPage"); //return the goods gid
        do {
            System.out.println("\n--------请选择您要更改的内容\n");
            System.out.println("\t1.更改商品-名称");
            System.out.println("\t2.更改商品-价格");
            System.out.println("\t3.更改商品-数量");
            System.out.println("\n请输入选项,或者按0返回上一级菜单.");

            String choice = InputString();// 输入信息.
            String regex = "[0-3]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        MainPage.MaintenancePage(); // 商品维护界面
                        break;
                    case 1:
                        GoodsService.updateGoodsName(gid); // 调用业务逻辑
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 2:
                        GoodsService.updateGoodsPrice(gid);// 调用业务逻辑
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 3:
                        GoodsService.updateGoodsNum(gid);// 调用业务逻辑
                        changedInfoNext("updateGoodsPage");
                        break;
                    default:
                        System.out.println("请输入正确的选择！");
                        break;
                }
            } else {
                PrintMenu.rollBackPrint();
                continue;
            }
            return;
        } while (true);
    }


    /**
     * 3.删除商品界面
     */
    public static void deleteGoodsPage() {
        System.out.println("\t正在执行 删除商品 操作\n");
        System.out.println("请输入想要删除的商品名字");

        //调用查找商品函数，显示将要删除的商品
        int gid = QueryPrint.query("deleteGoodsPage"); //return the goods gid

        //确认删除！
        do {
            System.out.println("\n确认删除该商品：Y/N");
            String choice = InputString();
            if ("y".equals(choice) || "Y".equals(choice)) {
                GoodsService.deleteGoods(gid);
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            } else {
                System.out.println("输入有误！");
                return;
            }
        } while (true);
    }


    /**
     * 4.查询商品界面
     */
    public static void queryGoodsPage() {
        System.out.println("\t\t  正在执行查询商品操作\n");
        System.out.println("\t\t1.按照商品 数量升序 查询");
        System.out.println("\t\t2.按照商品 价格升序 查询");
        System.out.println("\t\t3.输入商品  关键字  查询");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");

        do {
            String info = InputString();//用户选择上述提示信息
            String regex = "[0-3]";
            if (info.matches(regex)) {
                int choice = Integer.parseInt(info);
                switch (choice) {
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                    case 2:
                    case 3:
                        if (choice == 3) {//当用户使用3（即关键字查询）时，需要打印此项目。
                            System.out.println("\t\t正在执行商品  关键字  查询操作\n");
                            System.out.println("\n请输入商品关键字");
                        }
                        //调用查询功能
                        ArrayList<Goods> goodsList = new GoodsService().queryGoods(choice);
                        if (goodsList == null || goodsList.size() <= 0) {
                            System.err.println("\n\t!!您查询的商品不存在!!\n");
                            queryGoodsPage();
                        } else {
                            printTableHead(choice); // 打印表头

                            //遍历数组（存放用户查找的信息）
                            for (int i = 0, length = goodsList.size(); i < length; i++) {
                                Goods goods = goodsList.get(i);
                                System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
                                PrintMenu.printGoodsNumInfo(goods);
                            }
                            System.out.println("---------------------");
                            do {
                                System.out.println("输入 0 返回上一级菜单");
                                String choiceNext = InputString();

                                if ("0".equals(choiceNext)) {
                                    MainPage.MaintenancePage();
                                } else {
                                    System.out.println("输入有误！");
                                    return;
                                }
                            } while (true);
                        }
                        break;
                    default:
                        break;
                }
                break;
            }
            PrintMenu.rollBackPrint();
        } while (true);

        //用户选择操作完查询后的下一步
        System.out.println("\n\n输入 0 返回上一级菜单");
        boolean boolNext = true;
        do {
            String choice = InputString();
            if ("0".equals(choice)) {
                boolNext = false;
                queryGoodsPage();
            }
            PrintMenu.rollBackPrint();
        } while (boolNext);
    }

    private static void printTableHead(int choice) {
        if (choice == 1) //打印目录，不要放在功能函数中，会影响其他方法调用
        {
            System.out.println("\t\t\t\t\t商品按照 数量升序 列表\n\n");
        } else if (choice == 2) {
            System.out.println("\t\t\t\t\t商品按照 价格升序 列表\n\n");
        } else {
            System.out.println("\t\t\t\t\t您所查找的商品如下\n\n");
        }
        System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
    }


    /**
     * 5.展示所有商品界面
     */
    public static void displayGoodsPage() {
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodsList = new GoodsService().displayGoods();

        if (goodsList.size() <= 0) {
            System.err.println("！库存为空！");
            MainPage.MaintenancePage();
            return;
        }
        printGoodsInfo(goodsList);

        //下一步
        System.out.println("---------------------");
        do {
            System.out.println("输入 0 返回上一级菜单");
            String choice = InputString().trim();// 输入信息并去除前后的空格.
            if ("0".equals(choice)) {
                MainPage.MaintenancePage();
            } else {
                System.out.println("输入有误！");
                return;
            }
        } while (true);

    }

    private static void printGoodsInfo(ArrayList<Goods> goodsList) {
        System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
        for (Goods goods : goodsList) {
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + " $\t\t" + goods.getGnum());
            PrintMenu.printGoodsNumInfo(goods);
        }
    }


}
