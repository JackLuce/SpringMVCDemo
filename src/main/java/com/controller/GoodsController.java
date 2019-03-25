package com.controller;

import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.GoodsService;
import com.service.SaleDetailService;
import com.serviceimpl.GoodsServiceImpl;
import com.serviceimpl.SaleDetailServiceImpl;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class GoodsController {

    private  GoodsService goodsService = new GoodsServiceImpl() ;
    private  SaleDetailService saleDetailService = new SaleDetailServiceImpl();
    private List<Goods> listGoods = new ArrayList<Goods>();
    private List<SaleDetail> listSaleDetail = new ArrayList<SaleDetail>();

    @RequestMapping("/showGoods")
    public void showGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        listGoods = goodsService.findAll();
        listSaleDetail = saleDetailService.findAll();
        request.setAttribute("listGoods",listGoods );
        request.setAttribute("listSaleDetail",listSaleDetail );
        request.getRequestDispatcher("first.jsp").forward(request, response);
//        response.sendRedirect("first.jsp");
    }
    @ResponseBody
    @RequestMapping(value = "/selectGoods",method = RequestMethod.POST)
    public List<Goods> selectGoods(){
        listGoods = goodsService.findAll();
        return listGoods;
    }

    /**
     * 根据商品名称查找对应价格
     * @param goodsName
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/selectPriceByName",method = RequestMethod.POST)
    public void selectPriceByName(String goodsName,HttpServletRequest request, HttpServletResponse response){
        String name =request.getParameter("name");
        Goods goods = goodsService.findByName(name);
        System.out.println("goods----selectPriceByName-------"+goods);
        request.setAttribute("goods", goods);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //		out.write("{\"success\":\"success\"}");
            out.write("{\"goods\":\"+'"+goods.getPrice()+"'+\"}");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加多条商品销售记录
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/insertGoodsList",method = RequestMethod.POST)
    public String insertGoodsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("----------insertGoodsList------");
        List<SaleDetail> saleDetails = new ArrayList<SaleDetail>();
        //获取商品名称
        String nameStr = request.getParameter("name");
        String [] names = nameStr.split(",");
        //获取数量
        String numberStr = request.getParameter("number");
        String [] numberString = numberStr.split(",");
        int [] numberInt = new int [numberString.length];
        //获取小计
        String subTotalStr = request.getParameter("subTotal");
        String [] subTotalstring = subTotalStr.split(",");
        Double [] subTotalDouble = new Double [subTotalstring.length];
        Goods goods = new Goods();
        for (int i = 0; i<names.length;i++) {
            SaleDetail saleDetail =  new SaleDetail();
            goods = goodsService.findIDByName(names[i]);
            saleDetail.setGoodsNo(goods.getId());
            numberInt[i] = Integer.parseInt(numberString[i]);
            saleDetail.setNumber(numberInt[i]);
            subTotalDouble[i] = Double.parseDouble(subTotalstring[i]);
            saleDetail.setSubTotal(subTotalDouble[i]);
            saleDetails.add(saleDetail);
        }
        int resultS = saleDetailService.insert(saleDetails);
        System.out.println(resultS);
        PrintWriter out = response.getWriter();
        if(resultS>0) {
            System.out.println("销售情况表添加成功,添加了"+resultS+"条数据");
            out.write("{\"success\":\"+'"+resultS+"'+\"}");
            out.close();
        }else {
            out.write("{\"success\":\"error\"}");
            out.close();
        }
        return "";
    }
    @ResponseBody
    @RequestMapping(value = "/goodsSaleDetailsByPrice1000",method = RequestMethod.GET)
    public void goodsSaleDetailsByPrice1000(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GoodsSaleDetails> goodsSaleDetails = goodsService.findByPrice();
        for (GoodsSaleDetails goodsSaleDetails1:goodsSaleDetails) {
            System.out.println("----------------goodsSaleDetails---"+goodsSaleDetails1);
        }
        request.setAttribute("goodsSaleDetails", goodsSaleDetails);
        request.getRequestDispatcher("goodsSaleDetailsByPrice1000.jsp").forward(request, response);
    }

    public List<Goods> getListGoods() {
        return listGoods;
    }

    public void setListGoods(List<Goods> listGoods) {
        this.listGoods = listGoods;
    }

    public List<SaleDetail> getListSaleDetail() {
        return listSaleDetail;
    }

    public void setListSaleDetail(List<SaleDetail> listSaleDetail) {
        this.listSaleDetail = listSaleDetail;
    }
}
