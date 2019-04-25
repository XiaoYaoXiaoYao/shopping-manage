package entity;

/**
 * goods ��Ʒʵ����
 *
 * @author lyons(zhanglei)
 */
public final class Goods {
    /**
     * ��Ʒid
     */
    private int gid;

    /**
     * ��Ʒ����
     */
    private String gname;

    /**
     * ��Ʒ�۸�
     */
    private double gprice;

    /**
     * ��Ʒ����
     */
    private int gnum;

    /**
     * �����Ʒ��Ϣ
     */
    public Goods(String gname, double gprice, int gum) {
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gum;
    }

    /**
     * չʾ������Ʒ
     */
    public Goods(int gid, String gname, double gprice, int gum) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gum;
    }

    /**
     * ���ݱ�Ÿ�����Ʒ��Ϣ
     */
    public Goods(int gid, int gnum) {
        this.gid = gid;
        this.gnum = gnum;
    }

    /**
     * ���ݱ�Ÿ�����Ʒ��Ϣ
     */
    public Goods(int gid, double gprice) {
        this.gid = gid;
        this.gprice = gprice;
    }

    /**
     * ���ݱ�Ÿ�����Ʒ��Ϣ
     */
    public Goods(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    //����-get��set-������
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }
}
