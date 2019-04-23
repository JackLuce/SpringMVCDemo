package com.controller;

import com.entity.*;
import com.service.GoodsService;
import com.service.SaleDetailService;
import com.serviceimpl.GoodsServiceImpl;
import com.serviceimpl.SaleDetailServiceImpl;
import com.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class GoodsController {
    private  final Logger logger = LoggerFactory.getLogger(GoodsController.class);

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
        System.out.println("selectPriceByName--->"+goods);
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

    /**
     * 单价大于1000的商品销售情况
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/goodsSaleDetailsByPrice1000",method = RequestMethod.GET)
    public void goodsSaleDetailsByPrice1000(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GoodsSaleDetails> goodsSaleDetails = goodsService.findByPrice();
        for (GoodsSaleDetails goodsSaleDetails1:goodsSaleDetails) {
            System.out.println("goodsSaleDetailsByPrice1000--->"+goodsSaleDetails1);
        }
        request.setAttribute("goodsSaleDetails", goodsSaleDetails);
        request.getRequestDispatcher("goodsSaleDetailsByPrice1000.jsp").forward(request, response);
    }

    /**
     * 导出excle
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取销售id
        String saleNoStr = request.getParameter("saleNo");
        String[] saleNos = saleNoStr.split(",");
        //获取id
        String idsStr = request.getParameter("id");
        String[] ids = idsStr.split(",");
        //获取商品名称
        String nameStr = request.getParameter("name");
        String[] names = nameStr.split(",");
        //获取价格
        String priceStr = request.getParameter("price");
        String[] prices = priceStr.split(",");
        //获取数量
        String numberStr = request.getParameter("number");
        String[] numbers = numberStr.split(",");
        //获取小计
        String subTotalStr = request.getParameter("subTotal");
        String[] subTotals = subTotalStr.split(",");
        //获取数据
        List<GoodsSaleDetailsExcel> goodsSaleDetailsExcelList = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            GoodsSaleDetailsExcel goodsSaleDetailsExcel = new GoodsSaleDetailsExcel();
            goodsSaleDetailsExcel.setSaleNo(saleNos[i]);
            goodsSaleDetailsExcel.setId(ids[i]);
            goodsSaleDetailsExcel.setName(names[i]);
            goodsSaleDetailsExcel.setPrice(prices[i]);
            goodsSaleDetailsExcel.setNumber(numbers[i]);
            goodsSaleDetailsExcel.setSubTotal(subTotals[i]);
            goodsSaleDetailsExcelList.add(goodsSaleDetailsExcel);
        }

        //excel标题
        String[] title = {"商品ID", "商品名称", "单价", "数量", "小计"};

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = sdf.format(date);

        //excel文件名
        String fileName = "单价大于1000商品销售信息表" + currentTime + ".xls";

        //sheet名
        String sheetName = "单价大于1000商品销售信息表";

        //将数据装换为Excel文件的形式
        HSSFWorkbook hssfWorkbook= ExcelUtil.writeToExcel(goodsSaleDetailsExcelList,title,sheetName);

        response.reset(); // 清除buffer缓存
        response.setContentType("application/ms-excel;charset=UTF-8");
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename="
                .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(outputStream);
        bufferedOutPut.flush();
        hssfWorkbook.write(outputStream);
        bufferedOutPut.close();

        /**
         * 直接保存到指定位置
         */
        /*//将生成的Excel保存到指定位置
        String path ="E:/"+fileName;
        boolean saveExcelResult = ExcelUtil.saveExcel(path, hssfWorkbook);
        //读取指定位置下面的Excel文件内容
        List<GoodsSaleDetailsExcel> li =ExcelUtil.readExcel(path);

        for (int i = 0; i<li.size();i++){
            System.out.println("exportExcel--->"+li.get(i));
        }
        PrintWriter out = response.getWriter();
        if(saveExcelResult==true) {
            out.write("{\"success\":\"+'"+path+"'+\"}");
            out.close();
        }else {
            out.write("{\"success\":\"error\"}");
            out.close();
        }*/
        return fileName;
    }

    /**
     * 绘制饼图
     * @return
     */
    @ResponseBody
    @RequestMapping("/findGoodsSaledetailsChart")
    public String findGoodsSaledetailsChart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据集
        DefaultPieDataset data = new DefaultPieDataset();
        List<GoodsSaleDetailsChart> goodsSaleDetailsChartList = goodsService.findGoodsSaledetailsChart();
        for (GoodsSaleDetailsChart goodsChart : goodsSaleDetailsChartList) {
            System.out.println("-------"+goodsChart);
            data.setValue(goodsChart.getName(),goodsChart.getSumNumber());
        }
        JFreeChart chart = ChartFactory.createPieChart3D("商品销量图", // 图表标题
                data, // 数据集
                true, // 是否显示图例(对于简单的柱状图必须是 false)
                false, // 是否生成工具
                false // 是否生成 URL 链接
        );

        //中文乱码
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(new Font("黑体", Font.PLAIN, 20));
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

        // 写图表对象到文件，参照柱状图生成源码
        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("E:\\商品销量3DChart"+System.currentTimeMillis()+".jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 400, 300,
                    null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                request.getRequestDispatcher("showGoods").forward(request, response);
                response.sendRedirect("showGoods");
                fos_jpg.close();
            } catch (Exception e) {
            }
        }
        return "";
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
