package com.util;

import com.entity.GoodsSaleDetailsExcel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtil {
    /**
     * 将数据装换为Excel文件的形式
     * @param list
     * @param title
     * @param sheetName
     * @return
     */
    public static HSSFWorkbook writeToExcel(List list ,String[] title,String sheetName){
        //excel文件名
        String fileName = sheetName+System.currentTimeMillis()+".xls";
        System.out.println("fileName:"+fileName);
        String[][] content =new String[list.size()][title.length];
        System.out.println("list.size()："+list.size()+"---"+"title.length："+title.length);
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            GoodsSaleDetailsExcel goodsSaleDetailsExcel =(GoodsSaleDetailsExcel) list.get(i);
            for (int j = 0; j < title.length; j++) {
                content[i][j] = goodsSaleDetailsExcel.getString(j);
            }
        }
        //创建HSSFWorkbook
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        return workbook;

    }
    /**
     *响应到客户端:保存Excel文件到指定位置
     */
    public static boolean saveExcel(String path,HSSFWorkbook workbook){
        try {
            OutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /***
     * 读取excel
     * @param path
     * @return
     */
    public static List<GoodsSaleDetailsExcel> readExcel(String path){
        List<GoodsSaleDetailsExcel> goodsSaleDetailsExcelList = new ArrayList<>();
        HSSFWorkbook workbook = null;

        //读取excle文件
        try {
            InputStream inputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //循环工作表
        for (int numSheet = 0;numSheet<workbook.getNumberOfSheets();numSheet++){
            HSSFSheet hssfSheet  =workbook.getSheetAt(numSheet);
            if(hssfSheet == null){
                continue;
            }
            //循环行
            for (int rowNum = 0;rowNum<hssfSheet.getLastRowNum();rowNum++){
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int columnNum  = hssfRow.getPhysicalNumberOfCells();
                System.out.println("该表格的行数有："+hssfSheet.getLastRowNum()+"行"+columnNum+"列");
                if(hssfRow == null){
                    continue;
                }

                //将单元格中的内容存入集合
                HSSFCell hssfCell ;
                GoodsSaleDetailsExcel goodsSaleDetailsExcel = new GoodsSaleDetailsExcel();
                for (int i = 0; i<columnNum;i++){
                    hssfCell = hssfRow.getCell(i);
                    if(hssfCell == null){
                        continue;
                    }
                    String str = hssfCell.getStringCellValue();
                    goodsSaleDetailsExcel.setString(str,i);
                }
                goodsSaleDetailsExcelList.add(goodsSaleDetailsExcel);
            }
        }
        return goodsSaleDetailsExcelList;
    }

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setColumnWidth(0,3600);
        sheet.setColumnWidth(1,2600);
        sheet.setColumnWidth(2,2600);
        sheet.setColumnWidth(3,2600);
        sheet.setColumnWidth(4,2600);
        sheet.setColumnWidth(5,3600);
//        sheet.setColumnWidth((short) 0, (short) 2600);// 设置列宽
//        sheet.setColumnWidth((short) 1, (short) 2400);
//        sheet.setColumnWidth((short) 2, (short) 2300);
//        sheet.setColumnWidth((short) 3, (short) 1600);
//        sheet.setColumnWidth((short) 4, (short) 3600);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
        style.setWrapText(true); // 设置为自动换行
        HSSFFont cell_Font = (HSSFFont) wb.createFont();
        cell_Font.setFontName("宋体");
        cell_Font.setFontHeightInPoints((short) 8);
        style.setFont(cell_Font);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        //声明列对象
        HSSFCell cell = null;

        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        cellStyle.setFont(font);

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }


        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                HSSFCell cell1 = row.createCell(j);
                cell1.setCellValue(values[i][j]);
                cell1.setCellStyle(cellStyle);
            }
        }
        return wb;
    }
}