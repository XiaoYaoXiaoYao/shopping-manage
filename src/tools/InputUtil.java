package tools;

import java.util.Scanner;

/**
 * 1.各種完成操作后的 选择下一步
 * 2.界面选择操作
 *
 * @author lyons(zhanglei)
 */

public class InputUtil {
    /**
     * @return double 键盘获取商品价格,小数点后两位
     */
    public static double InputDouble() {
        double num;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("保留小数点后两位,请输入：");
            String info = sc.next();

            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//保留小数点后2位小数
            if (info.matches(regex)) {
                num = Double.parseDouble(info);
            } else {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        } while (true);

        return num;
    }

    /**
     * @return int 键盘获取商品数量
     */
    public static int InputInt() {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";//商品数量
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入：");
            String nums = sc.next();

            if (nums.matches(regex)) {
                num = Integer.parseInt(nums);
            } else {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        } while (true);
        return num;
    }

    /**
     * @return String 获取的键盘输入
     */
    public static String InputString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入：");
        return scanner.next();
    }
}
