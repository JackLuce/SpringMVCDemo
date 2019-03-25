package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 商品表
 * @author luominjie
 *
 */
public class Goods {

	/**
	 * 商品编号
	 */
	private  int   id;
	/**
	 * 商品名称
	 */
	private  String  name;
	/**
	 * 单价
	 */
	private  double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
