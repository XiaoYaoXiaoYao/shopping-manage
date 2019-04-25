package page;

import java.util.ArrayList;

import entity.*;
import service.GoodsService;
import service.SalesManService;
import tools.*;

import static tools.PrintMenu.*;
import static tools.InputUtil.*;

/**
 * �̳��������ϵͳ������
 *
 * @author ����
 * @version 1.0
 */

public final class MainPage {
    private static GoodsService goodsService = new GoodsService();
    private static SalesManService salesManService = new SalesManService();

    /**
     * ��ں���
     */
    public static void main(String[] args) {


        MainPage.mainPage();
    }

    /**
     * ������ ��ʵ�֣�
     */
    public static void mainPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.��Ʒά��\n");
            System.out.println("\t 2.ǰ̨����\n");
            System.out.println("\t 3.��Ʒ����\n");
            System.out.println("***************************");

            System.out.println("\n������ѡ��,���߰�0�˳�.");

            String choice = InputString();
            //������ʽ
            String regex = "[0-3]";
            if (choice.matches(regex)) {    // ��������Ƿ���0��1��2��3�С�
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        printEnd();// ���������Ϣ.
                        return;
                    case 1:
                        MaintenancePage(); // ������Ʒά������
                        break;
                    case 2:
                        checkStandLogPage(); // ����ǰ̨��������
                        break;
                    case 3:
                        commodityManagementPage(); //������Ʒ�������.
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);

    }


    /**
     * 1.��Ʒά������
     */
    public static void MaintenancePage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.�����Ʒ\n");
            System.out.println("\t 2.������Ʒ\n");
            System.out.println("\t 3.ɾ����Ʒ\n");
            System.out.println("\t 4.��ѯ��Ʒ\n");
            System.out.println("\t 5.��ʾ������Ʒ\n");
            System.out.println("***************************");
            System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");

            String choice = InputString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage(); // ����������
                        break;
                    case 1:
                        GoodsPage.addGoodsPage(); // ������Ʒ
                        break;
                    case 2:
                        GoodsPage.updateGoodsPage();// ������Ʒ����
                        break;
                    case 3:
                        GoodsPage.deleteGoodsPage();// ɾ����Ʒ����
                        break;
                    case 4:
                        GoodsPage.queryGoodsPage();// ��ѯ��Ʒ����
                        break;
                    case 5:
                        GoodsPage.displayGoodsPage(); // չʾ������Ʒ����
                        break;
                    default:
                        break;
                }
            }
            rollBackPrint();
        } while (true);
    }

    /**
     * 2.ǰ̨������½����
     */
    public static void checkStandLogPage() {
        do {
            System.out.println("\n*******��ӭʹ���̳��������ϵͳ*******\n");
            System.out.println("\t 1.��¼ϵͳ\n");
            System.out.println("\t 2.�˳�\n");
            System.out.println("-----------------------------");
            System.out.println("������ѡ��,���߰� 0 ������һ���˵�.");

            String choice = InputString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        int loginTimes = 3;//3�ε�½����
                        while (loginTimes != 0) {
                            loginTimes--;
                            login(loginTimes);
                        }
                        // ��½����������.
                        System.out.println("------------------");
                        System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
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
     * �û���½����ز���
     *
     * @param loginTimes ʣ���½����.
     */
    static void login(int loginTimes) {
        System.out.println("---�û���---");
        String sName = InputString();
        System.out.println("---����---");
        String sPssWord = InputString();

        ArrayList<SalesMan> salesManInfo = salesManService.checkstandLog(sName); //���û��������ݿ��л�ȡ�û�����.

        if (salesManInfo == null || salesManInfo.size() == 0) { //û�д��û��������
            System.err.println("\t!!�û�����������!!\n");
            System.out.println("\nʣ���½������" + loginTimes);
        } else {
            SalesMan salesMan = salesManInfo.get(0);//�˵أ�ֻ������һ����ֵ��ֻ����1�μ���

            if (sPssWord.equals(salesMan.getSPassWord())) { //��֤���룬��½�ɹ��ˣ���
                System.out.println("\t  ---�˻��ɹ���½---");
                shoppingSettlementPage(salesMan.getSId());//����ΪӪҵԱ���sId
            } else {
                System.err.println("\t!!�������!!\n");
                System.out.println("\nʣ���½������" + loginTimes);
            }
        }
    }

    /**
     * 3.��Ʒ�������
     */
    public static void commodityManagementPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.�ۻ�Ա����\n");
            System.out.println("\t 2.�г����������б�\n");
            System.out.println("***************************\n");

            System.out.println("������ѡ��,���߰� 0 ������һ���˵�.\n");
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
     * ����������
     */
    public static void shoppingSettlementPage(int salesManSid) {
        System.out.println("\n\t*******�������*******\n");
        do {
            System.out.println("�� S ��ʼ�������.�� 0 �����˻���¼����");
            String choNext = InputString();
            if ("0".equals(choNext)) {
                checkStandLogPage();
            } else if ("s".equals(choNext) || "S".equals(choNext)) {
                System.out.println("\n--��������Ʒ�ؼ���--");
                // ����Ʒ��������ֻ��һ��ʱ������Ʒgid�ţ���Ʒ���ۿ�ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
                int gid = QueryPrint.querySettlement();
                switch (gid) {
                    case -3:
                        break; //�޴���Ʒ,����ѭ��
                    case -1:
                        System.err.println("\t--��Ǹ������Ʒ���ۿ�--");
                        break;
                    default:
                        goodsService.choiceGoodsById(salesManSid);
                        break;
                }
            } else {
                System.err.println("\t!!������Ϸ��ַ�!!\n");
            }
        } while (true);
    }


    /**
     * �ۻ�Ա�������
     */
    public static void salesManManagementPage() {
        do {
            System.out.println("***************************\n");
            System.out.println("\t 1.����ۻ�Ա\n");
            System.out.println("\t 2.�����ۻ�Ա\n");
            System.out.println("\t 3.ɾ���ۻ�Ա\n");
            System.out.println("\t 4.��ѯ�ۻ�Ա\n");
            System.out.println("\t 5.��ʾ�����ۻ�Ա\n");
            System.out.println("***************************");

            System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");

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
