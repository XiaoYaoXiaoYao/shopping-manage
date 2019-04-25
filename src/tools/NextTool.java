package tools;

import page.GoodsPage;
import page.MainPage;
import page.SalesManPage;

/**
 * 因为是控制台项目，所以需要一种方式去控制用户选择的流程
 */
public class NextTool {

    /**
     * 获取用户-更改完商品-下一步
     * 获取用户-删除完商品-下一步
     * 获取用户-添加完商品-下一步
     *
     * @param oper 调用者
     */
    public static void changedInfoNext(String oper) {
        do {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = InputUtil.InputString();

            //在JAVA中: Equals比较的是值,==比较的是地址
            if ("y".equals(choice) || "Y".equals(choice)) {
                //下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
                if ("updateGoodsPage".equals(oper)) {
                    GoodsPage.updateGoodsPage();
                } else if ("deleteGoodsPage".equals(oper)) {
                    GoodsPage.deleteGoodsPage();
                } else if ("addGoodsPage".equals(oper)) {
                    GoodsPage.addGoodsPage();
                }
                //上面的嵌套结束
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            }
            System.out.println("\n输入有误！请重新输入.");
        } while (true);
    }

    /**
     * 获取用户-更改-完售货员-下一步
     * 获取用户-添加-完售货员-下一步
     * 获取用户-查询-完售货员-下一步
     * 获取用户-删除-完售货员-下一步
     *
     * @param oper 调用者
     */
    public static void choiceSalesManNext(String oper) {
        do {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = InputUtil.InputString();

            if ("y".equals(choice) || "Y".equals(choice)) {
                //下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
                if ("updateSalesMan".equals(oper)) {
                    SalesManPage.updateSalesManPage();
                } else if ("deleteSalesMan".equals(oper)) {
                    SalesManPage.deleteSalesManPage();
                } else if ("addSalesMan".equals(oper)) {
                    SalesManPage.addSalesManPage();
                } else if ("querySalesMan".equals(oper)) {
                    SalesManPage.querySalesManPage();
                }
                //上面的嵌套结束
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.salesManManagementPage();
            }
            System.err.println("\t输入有误！");
        } while (true);
    }
}
