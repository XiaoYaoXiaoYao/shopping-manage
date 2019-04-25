package tools;

import page.GoodsPage;
import page.MainPage;
import page.SalesManPage;

/**
 * ��Ϊ�ǿ���̨��Ŀ��������Ҫһ�ַ�ʽȥ�����û�ѡ�������
 */
public class NextTool {

    /**
     * ��ȡ�û�-��������Ʒ-��һ��
     * ��ȡ�û�-ɾ������Ʒ-��һ��
     * ��ȡ�û�-�������Ʒ-��һ��
     *
     * @param oper ������
     */
    public static void changedInfoNext(String oper) {
        do {
            System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
            String choice = InputUtil.InputString();

            //��JAVA��: Equals�Ƚϵ���ֵ,==�Ƚϵ��ǵ�ַ
            if ("y".equals(choice) || "Y".equals(choice)) {
                //�����Ƕ��if-else �����û�ѡ�����������ǰ��������ת��ָ��ҳ�档����Ϊ��ͬ�������ã���ת��ָ��������ͬ��
                if ("updateGoodsPage".equals(oper)) {
                    GoodsPage.updateGoodsPage();
                } else if ("deleteGoodsPage".equals(oper)) {
                    GoodsPage.deleteGoodsPage();
                } else if ("addGoodsPage".equals(oper)) {
                    GoodsPage.addGoodsPage();
                }
                //�����Ƕ�׽���
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            }
            System.out.println("\n������������������.");
        } while (true);
    }

    /**
     * ��ȡ�û�-����-���ۻ�Ա-��һ��
     * ��ȡ�û�-���-���ۻ�Ա-��һ��
     * ��ȡ�û�-��ѯ-���ۻ�Ա-��һ��
     * ��ȡ�û�-ɾ��-���ۻ�Ա-��һ��
     *
     * @param oper ������
     */
    public static void choiceSalesManNext(String oper) {
        do {
            System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
            String choice = InputUtil.InputString();

            if ("y".equals(choice) || "Y".equals(choice)) {
                //�����Ƕ��if-else �����û�ѡ�����������ǰ��������ת��ָ��ҳ�档����Ϊ��ͬ�������ã���ת��ָ��������ͬ��
                if ("updateSalesMan".equals(oper)) {
                    SalesManPage.updateSalesManPage();
                } else if ("deleteSalesMan".equals(oper)) {
                    SalesManPage.deleteSalesManPage();
                } else if ("addSalesMan".equals(oper)) {
                    SalesManPage.addSalesManPage();
                } else if ("querySalesMan".equals(oper)) {
                    SalesManPage.querySalesManPage();
                }
                //�����Ƕ�׽���
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.salesManManagementPage();
            }
            System.err.println("\t��������");
        } while (true);
    }
}
