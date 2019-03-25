package com.serviceimpl;

import java.util.List;

import com.dao.SaleDetailDao;
import com.daoimpl.SaleDetailDaoImpl;
import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;
import com.service.SaleDetailService;

public class SaleDetailServiceImpl implements SaleDetailService {
	
	private static SaleDetailDao saleDetailDao = new SaleDetailDaoImpl();

	public List<SaleDetail> findAll() {
		return saleDetailDao.findAll();
	}

	public int insert(List<SaleDetail> saleDetails) {
		return saleDetailDao.insert(saleDetails);
	}

	public int update(SaleDetail saleDetail) {
		return saleDetailDao.update(saleDetail);
	}

	public int delete(SaleDetail saleDetail) {
		return saleDetailDao.delete(saleDetail);
	}

	@Override
	public SaleDetail findByID(int id) {
		return saleDetailDao.findByID(id);
	}

	@Override
	public List<GoodsSaleDetails> findAllOf() {
		return saleDetailDao.findAllOf();
	}

}
