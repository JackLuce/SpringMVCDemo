package com.entity;
/**
 * 销售情况表
 * @author luominjie
 *
 */
public class SaleDetail {

	/**
	 * 销售单号
	 */
	private Long saleNo;
	/**
	 * 商品编号
	 */
	private int goodsNo;
	/**
	 * 数量
	 */
	private int number;
	/**
	 * 小计
	 */
	private double subTotal;
	
	public Long getSaleNo() {
		return saleNo;
	}
	public void setSaleNo(Long saleNo) {
		this.saleNo = saleNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	@Override
	public String toString() {
		return "SaleDetail [saleNo=" + saleNo + ", goodsNo=" + goodsNo + ", number=" + number + ", subTotal=" + subTotal
				+ "]";
	}
	
}
