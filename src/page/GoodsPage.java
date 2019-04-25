package page;

import java.util.ArrayList;

import entity.Goods;
import tools.PrintMenu;
import service.GoodsService;
import tools.QueryPrint;

import static tools.NextTool.*;
import static tools.InputUtil.*;

/**
 * ������Ʒ����
 *
 * @author lyons(zhanglei)
 */

public final class GoodsPage {
    /**
     * 1.�����Ʒ����
     */
    public static void addGoodsPage() {
        System.out.println("\t--------------����ִ�������Ʒ����-----------------\n");

        System.out.println("\nՈݔ�������Ʒ-����");
        String goodsName = InputString();

        System.out.println("\nՈݔ�������Ʒ-�۸�");
        double goodsPrice = InputDouble();

        System.out.println("\nՈݔ�������Ʒ-����");
        int goodsNumber = InputInt();

        Goods goods = new Goods(goodsName, goodsPrice, goodsNumber);
        boolean addRes = new GoodsService().addGoods(goods);

        if (addRes) {
            System.out.println("\n\t!���ѳɹ������Ʒ�����ݿ�!");
        } else {
            System.out.println("---------�����Ʒʧ��----------");
        }
        changedInfoNext("addGoodsPage");//ѡ����һ��
    }

    /**
     * 2.������Ʒ����
     */
    public static void updateGoodsPage() {
        System.out.println("\t����ִ�� ������Ʒ ����\n");
        System.out.println("��������Ҫ���ĵ���Ʒ����");

        //���ò�����Ʒ��������ʾ��Ҫ���ĵ���Ʒ��Ϣ
        int gid = QueryPrint.query("updateGoodsPage"); //return the goods gid
        do {
            System.out.println("\n--------��ѡ����Ҫ���ĵ�����\n");
            System.out.println("\t1.������Ʒ-����");
            System.out.println("\t2.������Ʒ-�۸�");
            System.out.println("\t3.������Ʒ-����");
            System.out.println("\n������ѡ��,���߰�0������һ���˵�.");

            String choice = InputString();// ������Ϣ.
            String regex = "[0-3]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        MainPage.MaintenancePage(); // ��Ʒά������
                        break;
                    case 1:
                        GoodsService.updateGoodsName(gid); // ����ҵ���߼�
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 2:
                        GoodsService.updateGoodsPrice(gid);// ����ҵ���߼�
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 3:
                        GoodsService.updateGoodsNum(gid);// ����ҵ���߼�
                        changedInfoNext("updateGoodsPage");
                        break;
                    default:
                        System.out.println("��������ȷ��ѡ��");
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
     * 3.ɾ����Ʒ����
     */
    public static void deleteGoodsPage() {
        System.out.println("\t����ִ�� ɾ����Ʒ ����\n");
        System.out.println("��������Ҫɾ������Ʒ����");

        //���ò�����Ʒ��������ʾ��Ҫɾ������Ʒ
        int gid = QueryPrint.query("deleteGoodsPage"); //return the goods gid

        //ȷ��ɾ����
        do {
            System.out.println("\nȷ��ɾ������Ʒ��Y/N");
            String choice = InputString();
            if ("y".equals(choice) || "Y".equals(choice)) {
                GoodsService.deleteGoods(gid);
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            } else {
                System.out.println("��������");
                return;
            }
        } while (true);
    }


    /**
     * 4.��ѯ��Ʒ����
     */
    public static void queryGoodsPage() {
        System.out.println("\t\t  ����ִ�в�ѯ��Ʒ����\n");
        System.out.println("\t\t1.������Ʒ �������� ��ѯ");
        System.out.println("\t\t2.������Ʒ �۸����� ��ѯ");
        System.out.println("\t\t3.������Ʒ  �ؼ���  ��ѯ");
        System.out.println("\n������ѡ��,���߰�0������һ���˵�.");

        do {
            String info = InputString();//�û�ѡ��������ʾ��Ϣ
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
                        if (choice == 3) {//���û�ʹ��3�����ؼ��ֲ�ѯ��ʱ����Ҫ��ӡ����Ŀ��
                            System.out.println("\t\t����ִ����Ʒ  �ؼ���  ��ѯ����\n");
                            System.out.println("\n��������Ʒ�ؼ���");
                        }
                        //���ò�ѯ����
                        ArrayList<Goods> goodsList = new GoodsService().queryGoods(choice);
                        if (goodsList == null || goodsList.size() <= 0) {
                            System.err.println("\n\t!!����ѯ����Ʒ������!!\n");
                            queryGoodsPage();
                        } else {
                            printTableHead(choice); // ��ӡ��ͷ

                            //�������飨����û����ҵ���Ϣ��
                            for (int i = 0, length = goodsList.size(); i < length; i++) {
                                Goods goods = goodsList.get(i);
                                System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
                                PrintMenu.printGoodsNumInfo(goods);
                            }
                            System.out.println("---------------------");
                            do {
                                System.out.println("���� 0 ������һ���˵�");
                                String choiceNext = InputString();

                                if ("0".equals(choiceNext)) {
                                    MainPage.MaintenancePage();
                                } else {
                                    System.out.println("��������");
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

        //�û�ѡ��������ѯ�����һ��
        System.out.println("\n\n���� 0 ������һ���˵�");
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
        if (choice == 1) //��ӡĿ¼����Ҫ���ڹ��ܺ����У���Ӱ��������������
        {
            System.out.println("\t\t\t\t\t��Ʒ���� �������� �б�\n\n");
        } else if (choice == 2) {
            System.out.println("\t\t\t\t\t��Ʒ���� �۸����� �б�\n\n");
        } else {
            System.out.println("\t\t\t\t\t�������ҵ���Ʒ����\n\n");
        }
        System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
    }


    /**
     * 5.չʾ������Ʒ����
     */
    public static void displayGoodsPage() {
        System.out.println("\t\t\t\t\t������Ʒ�б�\n\n");
        ArrayList<Goods> goodsList = new GoodsService().displayGoods();

        if (goodsList.size() <= 0) {
            System.err.println("�����Ϊ�գ�");
            MainPage.MaintenancePage();
            return;
        }
        printGoodsInfo(goodsList);

        //��һ��
        System.out.println("---------------------");
        do {
            System.out.println("���� 0 ������һ���˵�");
            String choice = InputString().trim();// ������Ϣ��ȥ��ǰ��Ŀո�.
            if ("0".equals(choice)) {
                MainPage.MaintenancePage();
            } else {
                System.out.println("��������");
                return;
            }
        } while (true);

    }

    private static void printGoodsInfo(ArrayList<Goods> goodsList) {
        System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
        for (Goods goods : goodsList) {
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + " $\t\t" + goods.getGnum());
            PrintMenu.printGoodsNumInfo(goods);
        }
    }


}
