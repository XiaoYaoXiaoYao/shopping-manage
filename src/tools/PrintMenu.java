package tools;

import entity.Goods;

/**
 * ����Щ�ظ��ʺܸߵĴ�ӡ�����ȡ������Ա��ڹ���
 */
public class PrintMenu {
    /**
     * ͳһ�����ذ�ť.
     */
    public static void rollBackPrint() {
        System.err.println("!��������!");
        System.out.println("��������� 0 ������һ���˵�.");
    }

    /**
     * ͳһ����˳���Ϣ.
     */
    public static void printEnd() {
        System.out.println("------------------");
        System.out.println("���Ѿ��˳�ϵͳ!");
        System.exit(1);//�˳����򣬷���ֵ�������
    }

    /**
     * ͳһ��ӡ��Ʒ������ص���Ϣ.
     */
    public static void printGoodsNumInfo(Goods goods) {
        int nums = goods.getGnum();
        printGoodsNumInfo(nums);
    }


    /**
     * ��ӡ��Ʒ�����������ʾ.
     */
    public static void printGoodsNumInfo(int nums) {
        if (nums == 0) {
            System.out.println("\t\t����Ʒ���ۿգ�");
        } else if (nums < 10) {
            System.out.println("\t\t����Ʒ�Ѳ���10��");
        } else {
            System.out.println("\t\t-");
        }
        System.out.println("\t");
    }

}
