package service;

import dao.GoodsDao;
import entity.Goods;
import entity.Gsales;
import tools.Arith;
import tools.NextTool;

import java.util.ArrayList;

import static page.MainPage.shoppingSettlementPage;
import static tools.InputUtil.*;

public class GoodsService {
    static GoodsDao goodsDao = new GoodsDao();

    public void choiceGoodsById(int salesManSid) {
        System.out.println("--����Ʒ���ѡ����Ʒ--");
        //����gid�����þ�ȷ��ѯ��Ʒ
        int shoppingGid = InputInt();

        ArrayList<Goods> goodsList = goodsDao.queryGoodsKey(shoppingGid, null);
        if (goodsList == null || goodsList.size() == 0) {
            System.err.println("\t�������޴���Ʒ ����\n");
        } else {
            Goods goods = goodsList.get(0);
            int gNum = goods.getGnum();
            double gPrice = goods.getGprice();

            System.out.println("--�����빺������--");
            do {
                int choiceGoodsNum = InputInt();//��ȡ�û�Ҫ���������

                if (choiceGoodsNum > gNum) {
                    System.err.println("\t�����ֿⴢ�����㣡��");
                    System.out.println("--���������빺������--");
                    continue;
                }
                double allPrice = Arith.mul(choiceGoodsNum, gPrice);//����BigDecimal���˷�����
                System.out.println("\t\t\t  ���ﳵ����\n");
                System.out.println("\t\t��Ʒ����\t��Ʒ����\t��������\t�ܼ�\n");
                System.out.println("\t\t" + goods.getGname() + "\t" + gPrice + " $\t" + choiceGoodsNum + "\t" + allPrice + " $\n");

                boolean buyRes = true;
                do {
                    System.out.println("ȷ�Ϲ���Y/N");
                    String choShopping = InputString();
                    if ("y".equals(choShopping) || "Y".equals(choShopping)) {
                        System.out.println("\n�ܼۣ�" + allPrice + " $");
                        System.out.println("\nʵ�ʽɷѽ��");

                        boolean res = true;
                        do {
                            res = GoodsService.buyGoods(salesManSid, goods, gNum, choiceGoodsNum, allPrice);
                        } while (res);
                        buyRes = false;
                    } else if ("N".equals(choShopping) || "n".equals(choShopping)) {
                        shoppingSettlementPage(salesManSid);
                        buyRes = false;
                    }
                    System.err.println("\t������ȷ�Ϲ������򣡣�");
                } while (buyRes);
            } while (true);
        }
    }

    public static boolean buyGoods(int salesManSid, Goods goods, int gNum, int choiceGoodsNum, double allPrice) {
        double amount = InputDouble();
        double balance = Arith.sub(amount, allPrice);  // �û���Ǯ�빺����Ʒ�ܼۼ�Ĳ��
        if (balance < 0) {
            System.err.println("\t�������ɽ��㣡��");
            System.out.println("\n������������ɽ��($)");
            return true;
        }
        /*	�����ǹ������������ݿ⣡����������
         * 1.����goods������
         * 2.����sales������
         * ԭ��Ʒ����gNum�� ����ԱId  salesManSid
         */

        //��sales�����
        Gsales gSales = new Gsales(goods.getGid(), salesManSid, choiceGoodsNum);
        boolean insert = new GsalesService().shoppingSettlement(gSales);

        //��goods�����
        int goodsNewNum = gNum - choiceGoodsNum; //����goods���и���Ʒ����
        Goods newGoods = new Goods(goods.getGid(), goodsNewNum);
        boolean update = new GoodsDao().updateGoods(3, newGoods);

        if (update && insert) {
            System.out.println("���㣺" + balance);
            System.out.println("\nлл���٣���ӭ�´λݹ�");
        } else {
            System.err.println("��֧��ʧ�ܣ�"); // �����������һ�������ݿ���������⣡
        }
        shoppingSettlementPage(salesManSid);//�����ת�����������ҳ��
        //	�����������������ݿ⣡����������-----------------------------------
        return false;

    }

    public static void updateGoodsNum(int gid) {
        System.out.println("��������Ʒ-������ ");
        int gNum = InputInt();
        Goods goodsNum = new Goods(gid, gNum);
        boolean boolNum = new GoodsDao().updateGoods(3, goodsNum);
        if (boolNum) {
            System.out.println("\n\t�����ɹ�������Ʒ���������ݿ⣡��\n");
        } else {
            System.err.println("\n\t����������Ʒ����ʧ������");
        }
    }

    public static void updateGoodsPrice(int gid) {
        System.out.println("��������Ʒ-�¼۸� ");
        double gprice = InputDouble();
        Goods goodsPrice = new Goods(gid, gprice);
        boolean boolPrice = new GoodsDao().updateGoods(2, goodsPrice);

        if (boolPrice) {
            System.out.println("\n\t�����ɹ�������Ʒ�۸������ݿ⣡��\n");
        } else {
            System.err.println("\n\t����������Ʒ�۸�ʧ������");
        }
    }

    public static void updateGoodsName(int gid) {
        System.out.println("��������Ʒ-������");
        String gname = InputString();
        Goods goodsName = new Goods(gid, gname);
        boolean boolName = new GoodsDao().updateGoods(1, goodsName);
        if (boolName) {
            System.out.println("\n\t�����ɹ�������Ʒ�������ݿ⣡��\n");
        } else {
            System.err.println("\n\t����������Ʒ��ʧ������");
        }
    }

    public static void deleteGoods(int gid) {
        //���Єh��-���ݿ����
        boolean boolDeleteGoods = new GoodsDao().deleteGoods(gid);//�{�Äh������

        if (boolDeleteGoods) {
            System.err.println("\t�����ѳɹ��h������Ʒ����\n");
        } else {
            System.err.println("\n\t�����h������Ʒʧ������");
        }
        NextTool.changedInfoNext("deleteGoodsPage");
    }

    public boolean addGoods(Goods goods) {
        return new GoodsDao().addGoods(goods);
    }

    public ArrayList<Goods> queryGoods(int choice) {
        return new GoodsDao().queryGoods(choice);
    }

    public ArrayList<Goods> displayGoods() {
        return new GoodsDao().displayGoods();
    }
}
