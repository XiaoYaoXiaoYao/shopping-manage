package page;

import java.util.ArrayList;

import dao.SalesManDao;
import entity.SalesMan;
import service.SalesManService;

import static tools.NextTool.choiceSalesManNext;
import static tools.InputUtil.*;

/**
 * �����ۻ�Ա����
 *
 * @author lyons(zhanglei)
 */

public final class SalesManPage {
    static SalesManDao salesManDao = new SalesManDao();
    /**
     * 1.����ۻ�Ա���� ��ʵ�֣�
     */
    public static void addSalesManPage() {
        System.out.println("\t����ִ������ۻ�Ա����\n");

        System.out.println("\n����ۻ�Ա-����");
        String name = InputString();

        System.out.println("\n����ۻ�Ա-����");
        String pwd = InputString();

        SalesMan salesMan = new SalesMan(name, pwd);
        boolean bool = new SalesManService().addSalesMan(salesMan);

        if (bool) {
            System.out.println("\n\t!���ѳɹ�����ۻ�Ա�����ݿ�!");
        } else {
            System.out.println("����ۻ�Աʧ��");
        }
        choiceSalesManNext("addSalesMan");
    }

    /**
     * 2.�����ۻ�Ա����
     */
    public static void updateSalesManPage() {
        System.out.println("\t����ִ�и����ۻ�Ա����\n");
        System.out.println("��������Ҫ���ĵ��ۻ�Ա����");
        String sName = InputString();

        //���þ�ȷ�����ۻ�Ա����
        ArrayList<SalesMan> salesManList = salesManDao.querySalesMan(sName);
        if (salesManList.size() <= 0) {
            System.err.println("\t�������޴��ˣ���");
            choiceSalesManNext("updateSalesMan");
        } else {
            //��ʾ��Ҫ���ĵ��ۻ�Ա��Ϣ
            System.out.println("\t\t\t�ۻ�Ա��Ϣ\n\n");
            System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

            SalesMan salesMan = salesManList.get(0); //����ľ�ȷ�����У�ֻ�ܷ���һ����ֵ�����������
            System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
            System.out.println();

            //ѡ������ۻ�Ա����
            System.out.println("\n--------��ѡ����Ҫ���ĵ�����\n");
            System.out.println("\t1.�����ۻ�Ա-����");
            System.out.println("\t2.�����ۻ�Ա-����");
            do {
                String choice = InputString();
                String regex = "[0-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            MainPage.salesManManagementPage();
                            break;
                        case 1:
                            System.out.println("�����ۻ�Ա-������");
                            String sNewName = InputString();

                            SalesMan salesManName = new SalesMan(salesMan.getSId(), sNewName, null);
                            boolean boolsName = new SalesManService().updateSalesMan(1, salesManName);

                            if (boolsName) {
                                System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
                            } else {
                                System.err.println("\n\t���������ۻ�Ա����ʧ������");
                            }
                            choiceSalesManNext("updateSalesMan");
                            break;
                        case 2:
                            System.out.println("�����ۻ�Ա-������");
                            String sNewPasswd = InputString();

                            SalesMan salesManPasswd = new SalesMan(salesMan.getSId(), null, sNewPasswd);
                            boolean boolsPasswd = new SalesManService().updateSalesMan(2, salesManPasswd);

                            if (boolsPasswd) {
                                System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
                            } else {
                                System.err.println("\n\t���������ۻ�Ա����ʧ������");
                            }
                            choiceSalesManNext("updateSalesMan");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println("\t!��������!");
                System.out.println("\n��ѡ��ѡ��.���߰� 0 ������һ���˵�.");
            } while (true);
        }
    }

    /**
     * 3.ɾ���ۻ�Ա����
     */
    public static void deleteSalesManPage() {

        System.out.println("\t����ִ�� ɾ���ۻ�Ա ����\n");
        System.out.println("��������Ҫɾ�����ۻ�Ա����");
        String sName = InputString();

        //���þ�ȷ�����ۻ�Ա����
        ArrayList<SalesMan> salesManList = salesManDao.querySalesMan(sName);
        if (salesManList.size() <= 0) {
            System.err.println("\t�������޴��ˣ���");
            choiceSalesManNext("deleteSalesMan");
        } else {
            //��ʾ��Ҫɾ�����ۻ�Ա��Ϣ
            System.out.println("\t\t\tɾ���ۻ�Ա��Ϣ\n\n");
            displayAllSalesMan(salesManList);
            //ȷ���Ƿ����ɾ����
            do {
                System.out.println("\nȷ��ɾ�����ۻ�Ա��Y/N");
                String choice = InputString();
                if ("y".equals(choice) || "Y".equals(choice)) {
                    //���Єh��-���ݿ����
                    boolean boolDeleteSalesMan = new SalesManService().deleteSalesMan(sName); //�{�Äh������

                    if (boolDeleteSalesMan) {
                        System.err.println("\t�����ѳɹ��h�����ۻ�Ա����\n");
                    } else {
                        System.err.println("\t�����h�����ۻ�Աʧ������");
                    }
                    choiceSalesManNext("deleteGoods");
                } else if ("N".equals(choice) || "n".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                System.err.println("\t!!��������,����������!!");
            } while (true);
        }
    }


    /**
     * 4.��ѯ�ۻ�Ա���� ��ʵ�֣�
     */
    public static void querySalesManPage() {
        System.out.println("\t\t  ����ִ�в�ѯ�ۻ�Ա����\n");
        System.out.println("Ҫ��ѯ���ۻ�Ա�ؼ���");
        String sName = InputString();

        ArrayList<SalesMan> salesManList = new SalesManService().querySalesMan(sName);

        if (salesManList.size() <= 0) {
            System.err.println("\t��û����Ա���ϲ�ѯ������");
        } else {
            displayAllSalesMan(salesManList);
        }
        choiceSalesManNext("querySalesMan"); //param��������
    }

    /**
     * 5.��ʾ�����ۻ�Ա����
     */
    public static void displaySalesManPage() {
        ArrayList<SalesMan> salesManList = new SalesManService().displaySalesMan();
        if (salesManList.size() <= 0) {
            System.err.println("\t�����ۻ�Ա�б�Ϊ�գ���");
            MainPage.salesManManagementPage();
        } else {
            displayAllSalesMan(salesManList);
            do {
                System.out.println("\n\n���� 0 ������һ���˵�");
                String choice = InputString();

                if ("0".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                System.err.print("\t��������");
            } while (true);
        }
    }

    static void displayAllSalesMan(ArrayList<SalesMan> salesManList) {
        System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
        System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

        for (SalesMan salesMan : salesManList) {
            System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
            System.out.println();
        }
    }
}