package com.sys.mgr.utils;

import com.sys.mgr.service.DataExportCommonProcessor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by zhengchenglei on 2017/12/5.
 */
public class ExcelDataExportUtil {




    /**
     * 数据导出工具
     * @param title
     * @param dataExportCommonProcessor
     * @param sheetSize 单个sheet页允许存放的数据量，超过将自动打开新的SHEET
     * @param response
     */
    public static void exportDataUtil(List<String> title, DataExportCommonProcessor dataExportCommonProcessor, Integer sheetSize,
                                      HttpServletResponse response) throws IOException {

        Object [][] data = dataExportCommonProcessor.getExportData();

        if(data == null){
            return ;
        }

        Workbook wb = new SXSSFWorkbook(2000);
        CellStyle titleCellStyle = generateTitleStype(wb);
        Font titleFont = generateFont(titleCellStyle, wb);
        Sheet sheet = wb.createSheet(0 + "");
        Row headerRow = sheet.createRow(0);
        generateExcelTitle(title,sheet, titleCellStyle, headerRow);
        int i = 0, offset = 0;
        while(data != null){
            if(offset >= (sheetSize == null ? 900000 : sheetSize)){
                sheet = wb.createSheet(++i + "");
                titleCellStyle = generateTitleStype(wb);
                titleFont = generateFont(titleCellStyle, wb);
                headerRow = sheet.createRow(0);
                generateExcelTitle(title, sheet, titleCellStyle, headerRow);
                offset = 0;
            }
            offset = generateExcelContent(data, wb, sheet, titleFont,offset);
            data = dataExportCommonProcessor.getExportData();
        }
        wb.write(getOutputStream(response));
    }



    /**
     *
     * @param data
     * @param wb
     * @param sheet
     * @param titleFont
     * @throws IOException
     */
    private static int generateExcelContent(Object [][] data, Workbook wb, Sheet sheet, Font titleFont,int offset) throws IOException {


        if (data == null) {
            return offset;
        }
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        Font font = wb.createFont();
        titleFont.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(font);

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(++ offset);
            for(int j = 0; j < data[i].length; j++) {
                generateExcelTitleContent(sheet, row, j, cellStyle, data[i][j] + "");
            }
        }

        return offset;

    }



    /**
     *
     * @param response
     * @return
     * @throws IOException
     */
    private static OutputStream getOutputStream(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
        return response.getOutputStream();
    }




    /**
     *
     * @param titleCellStyle
     * @param wb
     * @return
     */
    private static Font generateFont(CellStyle titleCellStyle,Workbook wb){
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleCellStyle.setFont(titleFont);
        return titleFont;
    }


    /**
     *
     * @param sheet
     * @param titleCellStyle
     * @param headerRow
     */
    private static void generateExcelTitle(List<String> title,Sheet sheet,CellStyle titleCellStyle,Row headerRow){

        for(int i = 0; i< title.size() ; i++) {
            generateExcelTitleContent(sheet, headerRow, i, titleCellStyle,title.get(i));
        }

    }


    /**
     * @param sheet
     * @param headerRow
     * @param index
     * @param titleCellStyle
     * @param titleName
     */
    private static void generateExcelTitleContent(Sheet sheet,Row headerRow,Integer index,CellStyle titleCellStyle,String titleName){

        Cell calcTimeCell = headerRow.createCell(index);
        calcTimeCell.setCellStyle(titleCellStyle);
        calcTimeCell.setCellValue(titleName);
        sheet.setColumnWidth(index, (40 * 160));
    }


    /**
     *
     * @param wb
     * @return
     */
    private static CellStyle generateTitleStype(Workbook wb){

        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        return titleCellStyle;
    }



}
