package com.serviceimpl;

import java.util.List;

import com.dao.GoodsDao;
import com.daoimpl.GoodsDaoImpl;
import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.GoodsSaleDetailsChart;
import com.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{

	private static GoodsDao goodsDao = new GoodsDaoImpl();
	
	public List<Goods> findAll() {
		return goodsDao.findAll();
	}

	public int insert(List<Goods> goodses) {
		return goodsDao.insert(goodses);
	}

	public int update(Goods goods) {
		return goodsDao.update(goods);
	}

	public int delete(Goods goods) {
		return goodsDao.delete(goods);
	}

	@Override
	public List<GoodsSaleDetailsChart> findGoodsSaledetailsChart() {
		return goodsDao.findGoodsSaledetailsChart();
	}

	@Override
	public List<GoodsSaleDetails> findByPrice() {
		return goodsDao.findByPrice();
	}

	@Override
	public Goods findByID(int id) {
		return goodsDao.findByID(id);
	}

	@Override
	public Goods findIDByName(String name) {
		return goodsDao.findIDByName(name);
	}

	@Override
	public Goods findByName(String name) {
		return goodsDao.findByName(name);
	}

}
