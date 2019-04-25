package page;

import java.util.ArrayList;

import entity.Gsales;
import service.GsalesService;
import tools.PrintMenu;
import tools.InputUtil;

/**
 * ����������Ʒ�б����
 *
 * @author lyons(zhanglei)
 */

public final class GsalesPage {
    public static void dailySaleGoodsPage() {
        System.out.println("\t����ִ���г������۳���Ʒ�б����\n");
        ArrayList<Gsales> GsalesList = new GsalesService().dailyGsales();//�����۳���Ʒ���鼯

        if (GsalesList.size() <= 0) {
            System.err.println("\t������������Ʒ�۳�����");
            MainPage.commodityManagementPage();
        } else {
            System.out.println("\t\t\t\t�����۳���Ʒ�б�\n");
            System.out.println("\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t����\t\t��ע\n");

            for (int i = 0, length = GsalesList.size(); i < length; i++) {
                //��ȡ�۳���Ʒ��gname,gprice,gnum, allSum (������Ʒ�������ܺ�)
                Gsales gSales = GsalesList.get(i);
                System.out.print("\t" + gSales.getGName() + "\t\t" + gSales.getGPrice() + " $\t\t" + gSales.getGNum() + "\t\t" + gSales.getAllSnum());
                int gNUm = gSales.getGNum();
                PrintMenu.printGoodsNumInfo(gNUm);
            }
            do {
                System.out.println("\n\n���� 0 ������һ���˵�");
                String choice = InputUtil.InputString();
                if ("0".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                MainPage.commodityManagementPage();
            } while (true);
        }
    }
}
