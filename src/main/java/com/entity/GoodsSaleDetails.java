package com.entity;

/**
 * 商品销售合计
 * @author luominjie
 *
 */
public class GoodsSaleDetails {

	private Goods goods;
	private SaleDetail saleDetail;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
	@Override
	public String toString() {
		return "GoodsSaleDetails [goods=" + goods + ", saleDetail=" + saleDetail + "]";
	}
	
}
