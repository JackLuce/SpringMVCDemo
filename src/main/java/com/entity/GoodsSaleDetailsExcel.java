package com.entity;

/**
 * 封装导出Excel的对象
 */
public class GoodsSaleDetailsExcel {

    private String id;
    private String name;
    private String price;
    private String number;
    private String subTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "GoodsSaleDetailsExcel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", subTotal='" + subTotal + '\'' +
                '}';
    }

    public void setString(String str,int j){
        if(j<0){
            System.out.println("列数的起始下标必须大于等于0!");
            return;
        }
        //此处需要注意title内容与列数相匹配
        switch (j) {
            case 0:setId(str);
                break;
            case 1:setName(str);
                break;
            case 2:setPrice(str);
                break;
            case 3:setNumber(str);
                break;
            case 4:setSubTotal(str);
                break;
            default:
                break;
        }
    }
    public String getString(int j){
        String str="";

        //此处需要注意title内容与列数相匹配
        switch (j) {
            case 0:
                str = getId();
                break;
            case 1:
                str = getName();
                break;
            case 2:
                str = getPrice();
                break;
            case 3:
                str = getNumber();
                break;
            case 4:
                str = getSubTotal();
                break;
            default:
                break;
        }
        return str;
    }
}
