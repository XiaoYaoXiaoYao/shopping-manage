package tools;

import entity.Goods;

/**
 * 将这些重复率很高的打印语句提取至这里，以便于管理。
 */
public class PrintMenu {
    /**
     * 统一管理返回按钮.
     */
    public static void rollBackPrint() {
        System.err.println("!输入有误!");
        System.out.println("重新输入或按 0 返回上一级菜单.");
    }

    /**
     * 统一输出退出信息.
     */
    public static void printEnd() {
        System.out.println("------------------");
        System.out.println("您已经退出系统!");
        System.exit(1);//退出程序，返回值随便设置
    }

    /**
     * 统一打印商品数量相关的信息.
     */
    public static void printGoodsNumInfo(Goods goods) {
        int nums = goods.getGnum();
        printGoodsNumInfo(nums);
    }


    /**
     * 打印商品数量的相关提示.
     */
    public static void printGoodsNumInfo(int nums) {
        if (nums == 0) {
            System.out.println("\t\t该商品已售空！");
        } else if (nums < 10) {
            System.out.println("\t\t该商品已不足10件");
        } else {
            System.out.println("\t\t-");
        }
        System.out.println("\t");
    }

}
