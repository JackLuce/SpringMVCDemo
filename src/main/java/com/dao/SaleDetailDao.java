package com.dao;

import java.util.List;

import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;

public interface SaleDetailDao {
	
	/**
	 * 查询商品和销售表所有
	 * @return
	 */
	public List<GoodsSaleDetails> findAllOf();

	/**
	 * 查找所有
	 * @return
	 */
	public List<SaleDetail> findAll();
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public SaleDetail findByID(int id);
	/**
	 * 添加
	 * @param
	 * @return
	 */
	public int insert(List<SaleDetail> saleDetails);
	/**
	 * 更新
	 * @param saleDetail
	 * @return
	 */
	public int update(SaleDetail saleDetail);
	/**
	 * 删除
	 * @param saleDetail
	 * @return
	 */
	public int delete(SaleDetail saleDetail);
}
