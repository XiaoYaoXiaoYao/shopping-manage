package service;

import dao.SalesManDao;
import entity.SalesMan;

import java.util.ArrayList;

public class SalesManService {
    SalesManDao salesManDao = new SalesManDao();

    public ArrayList<SalesMan> checkstandLog(String sName) {
        return salesManDao.checkstandLog(sName);
    }

    public boolean addSalesMan(SalesMan salesMan) {
        return salesManDao.addSalesMan(salesMan);
    }

    public ArrayList<SalesMan> displaySalesMan() {
        return salesManDao.displaySalesMan();
    }

    public boolean updateSalesMan(int i, SalesMan salesManName) {
        return salesManDao.updateSalesMan(i, salesManName);
    }

    public boolean deleteSalesMan(String sName) {
        return salesManDao.deleteSalesMan(sName);
    }

    public ArrayList<SalesMan> querySalesMan(String sName) {
        return salesManDao.querySalesMan(sName);
    }
}
