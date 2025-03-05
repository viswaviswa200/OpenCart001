package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    public String path;


    public ExcelUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int row_count = sheet.getLastRowNum();
        wb.close();
        fi.close();
        return row_count;
    }

    public int getCellCount(String sheetName,int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int cell_count = sheet.getRow(rownum).getLastCellNum();
        wb.close();
        fi.close();
        return cell_count;
    }

    public String getCellData(String sheetName,int rownum,int cellnum) throws  IOException{
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        }
        catch (Exception e){
            data =  "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName,int rownum,int cellnum,String data) throws IOException{
        File xfile = new File(path);
        if(!xfile.exists()){
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
        }

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);

        if(wb.getSheetIndex(sheetName)==-1) wb.createSheet(sheetName);

        sheet = wb.getSheet(sheetName);

        if(sheet.getRow(rownum)==null) sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        if(row.getCell(cellnum)==null) row.createCell(cellnum);
        row.getCell(cellnum).setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColor(String sheetName,int rownum,int cellnum) throws IOException{
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public void fillRedColor(String sheetName,int rownum,int cellnum) throws IOException{
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}
