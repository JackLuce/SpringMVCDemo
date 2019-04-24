package com.entity;

/**
 * 封装数据图表对象
 */
public class GoodsSaleDetailsChart {

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品订单数
     */
    private int count;
    /**
     * 商品销售数量总和
     */
    private long sumNumber;
    /**
     * 对应商品销售小计总和
     */
    private long sumSubTotal;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(long sumNumber) {
        this.sumNumber = sumNumber;
    }

    public long getSumSubTotal() {
        return sumSubTotal;
    }

    public void setSumSubTotal(long sumSubTotal) {
        this.sumSubTotal = sumSubTotal;
    }

    @Override
    public String toString() {
        return "GoodsSaleDetailsChart{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", sumNumber=" + sumNumber +
                ", sumSubTotal=" + sumSubTotal +
                '}';
    }
}
