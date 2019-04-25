package service;

import dao.GsalesDao;
import entity.Gsales;

import java.util.ArrayList;

public class GsalesService {
    public boolean shoppingSettlement(Gsales gSales) {
        return new GsalesDao().shoppingSettlement(gSales);
    }

    public ArrayList<Gsales> dailyGsales() {
        // 不想支持这个功能，嫌弃.
        return new ArrayList<Gsales>();
    }
}
