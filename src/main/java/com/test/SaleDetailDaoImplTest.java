package com.test;

import com.dao.SaleDetailDao;
import com.daoimpl.SaleDetailDaoImpl;
import com.entity.GoodsSaleDetails;
import org.junit.Test;

import java.util.List;


public class SaleDetailDaoImplTest {
    public SaleDetailDao saleDetailDao= new SaleDetailDaoImpl();
    @Test
    public void findGoodsSaleDeatilsByName() {
        List<GoodsSaleDetails> deatils = saleDetailDao.findGoodsSaleDeatilsByName("笔记本");
        for (GoodsSaleDetails d:deatils) {
            System.out.println(d);
        }
    }
}
