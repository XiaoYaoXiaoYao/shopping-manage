package entity;

/**
 * goods 商品实体类
 *
 * @author lyons(zhanglei)
 */
public final class Goods {
    /**
     * 商品id
     */
    private int gid;

    /**
     * 商品名称
     */
    private String gname;

    /**
     * 商品价格
     */
    private double gprice;

    /**
     * 商品数量
     */
    private int gnum;

    /**
     * 添加商品信息
     */
    public Goods(String gname, double gprice, int gum) {
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gum;
    }

    /**
     * 展示所有商品
     */
    public Goods(int gid, String gname, double gprice, int gum) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gum;
    }

    /**
     * 根据编号更改商品信息
     */
    public Goods(int gid, int gnum) {
        this.gid = gid;
        this.gnum = gnum;
    }

    /**
     * 根据编号更改商品信息
     */
    public Goods(int gid, double gprice) {
        this.gid = gid;
        this.gprice = gprice;
    }

    /**
     * 根据编号更改商品信息
     */
    public Goods(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    //共有-get、set-方法。
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
