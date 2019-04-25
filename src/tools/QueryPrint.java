package tools;

import java.util.ArrayList;

import dao.GoodsDao;
import entity.Goods;

import static tools.NextTool.changedInfoNext;
import static tools.PrintMenu.printGoodsNumInfo;

/**
 * ��ѯ&&��ӡ ��������(�����Ż����ܻ�ɾ)
 *
 * @author lyons(zhanglei)
 */
public final class QueryPrint {

    /**
     * ģ����ѯ�����в�ѯ��Ϣ����С����
     *
     * @param oper ������
     * @return ��ѯ������Ϣ��gid, �������ֵ����-1��������ѯ�쳣��
     */
    public static int query(String oper) {
        int gid = -1;
        String shopping = InputUtil.InputString(); //���̻�ȡ��Ʒ����
        ArrayList<Goods> goodsList = new GoodsDao().queryGoodsKey(-1, shopping);  //���ݼ��̻�ȡ����Ʒ�����{�� ��ȷ��ѯ�������_���Ñ���Ҫ����������
        if (goodsList == null || goodsList.size() <= 0) {
            System.err.println("\t�������޴���Ʒ ����");

            //�{��ѡ����һ������
            changedInfoNext(oper);

        } else { //�鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������
            Goods goods = goodsList.get(0);

            System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
            System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
            printGoodsNumInfo(goods);

            gid = goods.getGid(); //����Ʒ��ŷ��ظ�������
        }
        return gid;
    }

    /**
     * ģ����ѯ����С����
     *
     * @return int ����Ʒ��������ֻ��һ��ʱ������Ʒgid�ţ���Ʒ���ۿ�ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
     */
    public static int querySettlement() {
        int gid = -1;
        ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);//�{�� �ؼ��ֲ�ѯ����
        if (goodsSettlement == null || goodsSettlement.size() <= 0) {
            System.err.println("\t�������޴���Ʒ ����\n");
            gid = -3;
        } else {  //�鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������
            System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
            System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
            for (int i = 0; i < goodsSettlement.size(); i++) {
                Goods goods = goodsSettlement.get(i);
                if (goods.getGnum() > 0) {
                    System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());

                    printGoodsNumInfo(goods);

                    if (goodsSettlement.size() == 1) {
                        gid = goods.getGid(); //����Ʒ��ŷ��ظ�������
                    } else {
                        gid = -2;
                    }
                }
            }
        }
        return gid;
    }
}
