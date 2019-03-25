package com.test;

import com.dao.GoodsDao;
import com.dao.SaleDetailDao;
import com.daoimpl.GoodsDaoImpl;
import com.daoimpl.SaleDetailDaoImpl;
import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;
import com.service.GoodsService;
import com.service.SaleDetailService;
import com.serviceimpl.GoodsServiceImpl;
import com.serviceimpl.SaleDetailServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class JDBCTest {

	public GoodsDao goodsDao = new GoodsDaoImpl();
	public SaleDetailDao  saleDetailDao= new SaleDetailDaoImpl();
	private static GoodsService goodsService =new  GoodsServiceImpl();
	private static SaleDetailService saleDetailService =new  SaleDetailServiceImpl();
    @org.junit.Test
	public void test1() {
//		List<Goods> listGoods =  goodsDao.findAll();
		List<Goods> listGoods =  goodsService.findAll();
		for (Goods goods : listGoods) {
			System.out.println(goods);
		}

	}
    @org.junit.Test
	public void test2() {
		List<Goods> goodses = new ArrayList<Goods>();
		Goods goods = new Goods();
		goods.setId(66);
		goods.setName("fff");
		goods.setPrice(100.00);
		goodses.add(goods);
		int result = goodsService.insert(goodses);
		System.out.println(result);
		goods.setName("改名");
		int res = goodsService.update(goods);
		System.out.println(res+"update");
	}
    @org.junit.Test
	public void test3() {
		List<GoodsSaleDetails> g = goodsService.findByPrice();
		for (GoodsSaleDetails goodsSaleDetails : g) {
			System.out.println(goodsSaleDetails);
		}
	}
    @org.junit.Test
	public void test5() {
		SaleDetail SaleDetail = saleDetailService.findByID(4);
		System.out.println(SaleDetail);
	}
    @org.junit.Test
	public void test6() {
		Goods goods = goodsService.findIDByName("笔记本");
		System.out.println(goods);
	}
    @org.junit.Test
	public void test7() {
		String name = "笔记本";
		Goods goods = goodsService.findByName(name);
		System.out.println(goods);
	}
    @org.junit.Test
	public void test8() {
		List<GoodsSaleDetails> gList  =saleDetailService.findAllOf();
		for (GoodsSaleDetails goodsSaleDetails : gList) {
			System.out.println(goodsSaleDetails);
		}
	}
}
