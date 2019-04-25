package tools;

import java.util.Scanner;

/**
 * 1.���N��ɲ������ ѡ����һ��
 * 2.����ѡ�����
 *
 * @author lyons(zhanglei)
 */

public class InputUtil {
    /**
     * @return double ���̻�ȡ��Ʒ�۸�,С�������λ
     */
    public static double InputDouble() {
        double num;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("����С�������λ,�����룺");
            String info = sc.next();

            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//����С�����2λС��
            if (info.matches(regex)) {
                num = Double.parseDouble(info);
            } else {
                System.err.println("����������");
                continue;
            }
            break;
        } while (true);

        return num;
    }

    /**
     * @return int ���̻�ȡ��Ʒ����
     */
    public static int InputInt() {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";//��Ʒ����
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("�����룺");
            String nums = sc.next();

            if (nums.matches(regex)) {
                num = Integer.parseInt(nums);
            } else {
                System.err.println("����������");
                continue;
            }
            break;
        } while (true);
        return num;
    }

    /**
     * @return String ��ȡ�ļ�������
     */
    public static String InputString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("�����룺");
        return scanner.next();
    }
}
