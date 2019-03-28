package com.dao;

import java.util.List;

import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.GoodsSaleDetailsChart;

public interface GoodsDao {

	/**
	 * 单价>1000的商品
	 * @return
	 */
	public List<GoodsSaleDetails> findByPrice();
	/**
	 * 查找所有
	 * @return
	 */
	public List<Goods> findAll();
	/**
	 * findByName
	 * @param name
	 * @return
	 */
	public Goods findByName(String name);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Goods findByID(int id);
	/**
	 * 根据商品名称获取id
	 * @return
	 */
	public Goods findIDByName(String name);
	/**
	 * 添加
	 * @param
	 * @return
	 */
	public int insert(List<Goods> goodses);
	/**
	 * 更新
	 * @param goods
	 * @return
	 */
	public int update(Goods goods);
	/**
	 * 删除
	 * @param goods
	 * @return
	 */
	public int delete(Goods goods);

	/**
	 * 查询绘制图表数据
	 * @return
	 */
	public List<GoodsSaleDetailsChart> findGoodsSaledetailsChart();
}
