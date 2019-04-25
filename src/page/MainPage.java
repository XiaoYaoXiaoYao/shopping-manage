package page;

import java.util.ArrayList;

import entity.*;
import service.GoodsService;
import service.SalesManService;
import tools.*;

import static tools.PrintMenu.*;
import static tools.InputUtil.*;

/**
 * 商超购物管理系统主界面
 *
 * @author 张磊
 * @version 1.0
 */

public final class MainPage {
    private static GoodsService goodsService = new GoodsService();
    private static SalesManService salesManService = new SalesManService();

    /**
     * 入口函数
     */
    public static void main(String[] args) {


        MainPage.mainPage();
    }

    /**
     * 主界面 已实现！
     */
    public static void mainPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.商品维护\n");
            System.out.println("\t 2.前台收银\n");
            System.out.println("\t 3.商品管理\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按0退出.");

            String choice = InputString();
            //正则表达式
            String regex = "[0-3]";
            if (choice.matches(regex)) {    // 检查输入是否在0，1，2，3中。
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        printEnd();// 输出结束信息.
                        return;
                    case 1:
                        MaintenancePage(); // 进入商品维护界面
                        break;
                    case 2:
                        checkStandLogPage(); // 进入前台收银界面
                        break;
                    case 3:
                        commodityManagementPage(); //进入商品管理界面.
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);

    }


    /**
     * 1.商品维护界面
     */
    public static void MaintenancePage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.添加商品\n");
            System.out.println("\t 2.更改商品\n");
            System.out.println("\t 3.删除商品\n");
            System.out.println("\t 4.查询商品\n");
            System.out.println("\t 5.显示所有商品\n");
            System.out.println("***************************");
            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");

            String choice = InputString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage(); // 返回主界面
                        break;
                    case 1:
                        GoodsPage.addGoodsPage(); // 新增商品
                        break;
                    case 2:
                        GoodsPage.updateGoodsPage();// 更改商品界面
                        break;
                    case 3:
                        GoodsPage.deleteGoodsPage();// 删除商品界面
                        break;
                    case 4:
                        GoodsPage.queryGoodsPage();// 查询商品界面
                        break;
                    case 5:
                        GoodsPage.displayGoodsPage(); // 展示所有商品界面
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);
    }

    /**
     * 2.前台收银登陆界面
     */
    public static void checkStandLogPage() {
        do {
            System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
            System.out.println("\t 1.登录系统\n");
            System.out.println("\t 2.退出\n");
            System.out.println("-----------------------------");
            System.out.println("请输入选项,或者按 0 返回上一级菜单.");

            String choice = InputString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        int loginTimes = 3;//3次登陆机会
                        while (loginTimes != 0) {
                            loginTimes--;
                            login(loginTimes);
                        }
                        // 登陆次数已用完.
                        System.out.println("------------------");
                        System.err.println("\t！！您已被强制退出系统！！");
                        System.exit(1);
                        break;
                    case 2:
                        printEnd();
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);
    }

    /**
     * 用户登陆的相关操作
     *
     * @param loginTimes 剩余登陆次数.
     */
    static void login(int loginTimes) {
        System.out.println("---用户名---");
        String sName = InputString();
        System.out.println("---密码---");
        String sPssWord = InputString();

        ArrayList<SalesMan> salesManInfo = salesManService.checkstandLog(sName); //以用户名从数据库中获取用户密码.

        if (salesManInfo == null || salesManInfo.size() == 0) { //没有此用户的情况！
            System.err.println("\t!!用户名输入有误!!\n");
            System.out.println("\n剩余登陆次数：" + loginTimes);
        } else {
            SalesMan salesMan = salesManInfo.get(0);//此地，只返回了一组数值，只遍历1次即可

            if (sPssWord.equals(salesMan.getSPassWord())) { //验证密码，登陆成功了！！
                System.out.println("\t  ---账户成功登陆---");
                shoppingSettlementPage(salesMan.getSId());//参数为营业员编号sId
            } else {
                System.err.println("\t!!密码错误!!\n");
                System.out.println("\n剩余登陆次数：" + loginTimes);
            }
        }
    }

    /**
     * 3.商品管理界面
     */
    public static void commodityManagementPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.售货员管理\n");
            System.out.println("\t 2.列出当日卖出列表\n");
            System.out.println("***************************\n");

            System.out.println("请输入选项,或者按 0 返回上一级菜单.\n");
            String choice = InputString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        salesManManagementPage();
                        break;
                    case 2:
                        GsalesPage.dailySaleGoodsPage();
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);
    }


    /**
     * 购物结算界面
     */
    public static void shoppingSettlementPage(int salesManSid) {
        System.out.println("\n\t*******购物结算*******\n");
        do {
            System.out.println("按 S 开始购物结算.按 0 返回账户登录界面");
            String choNext = InputString();
            if ("0".equals(choNext)) {
                checkStandLogPage();
            } else if ("s".equals(choNext) || "S".equals(choNext)) {
                System.out.println("\n--请输入商品关键字--");
                // 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
                int gid = QueryPrint.querySettlement();
                switch (gid) {
                    case -3:
                        break; //无此商品,重新循环
                    case -1:
                        System.err.println("\t--抱歉，该商品已售空--");
                        break;
                    default:
                        goodsService.choiceGoodsById(salesManSid);
                        break;
                }
            } else {
                System.err.println("\t!!请输入合法字符!!\n");
            }
        } while (true);
    }


    /**
     * 售货员管理界面
     */
    public static void salesManManagementPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.添加售货员\n");
            System.out.println("\t 2.更改售货员\n");
            System.out.println("\t 3.删除售货员\n");
            System.out.println("\t 4.查询售货员\n");
            System.out.println("\t 5.显示所有售货员\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");

            String choice = InputString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        commodityManagementPage();
                        break;
                    case 1:
                        SalesManPage.addSalesManPage();
                        break;
                    case 2:
                        SalesManPage.updateSalesManPage();
                        break;
                    case 3:
                        SalesManPage.deleteSalesManPage();
                        break;
                    case 4:
                        SalesManPage.querySalesManPage();
                        break;
                    case 5:
                        SalesManPage.displaySalesManPage();
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);
    }
}
