package poi;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIMergen {
	
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("D:\\15.xls");  
		  
		HSSFWorkbook wb = new HSSFWorkbook();  
		  
		/** 
		 * ======================================================== 
		 *                          设置cell宽度 
		 *  通过sheet 对象，setColumnWidth设置cell的宽度 
		 * ======================================================== 
		 */  
		HSSFSheet sheet = wb.createSheet("sheet1");  
		// api 段信息 Set the width (in units of 1/256th of a character width)  
		sheet.setColumnWidth(0, 20 * 256);  
		
		  
		/** 
		 * ======================================================== 
		 *                          设置行高度 
		 *  通过row 对象设置行高 
		 * ======================================================== 
		 */  
		HSSFRow row = sheet.createRow(0);  
		//heightInPoints 设置的值永远是height属性值的20倍  
		row.setHeightInPoints(20);  
		  
		HSSFRow row1 = sheet.createRow(5);  
		// Set the row's height or set to ff (-1) for undefined/default-height.  
		// Set the height in "twips" or  
		// 1/20th of a point.  
		row1.setHeight((short) (25 * 20));  
		  
		HSSFCell cell = row.createCell(0);  
		  
//		cell.setCellValue();  
		  
		//设置默认宽度、高度值          
		HSSFSheet sheet2 =  wb.createSheet("sheet2");  
		          
		sheet2.setDefaultColumnWidth(20);  
		sheet2.setDefaultRowHeightInPoints(20);  
		  
		              //格式化单元格日期信息  
		HSSFDataFormat dataFormat =  wb.createDataFormat();  
		short dataformat = dataFormat.getFormat("yyyy-mm-dd HH:MM");  
		HSSFCellStyle style = wb.createCellStyle();  
		  
		  
		style.setDataFormat(dataformat);  
		  
		  
		HSSFCell cell2 = sheet2.createRow(0).createCell(0);  
		cell2.setCellValue(new Date());  
		  
		cell2.setCellStyle(style);  
		  
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 5); 
		Sheet sheet3=wb.createSheet("sheet3");
		sheet3.setDefaultRowHeightInPoints(20);
		sheet3.setDefaultColumnWidth(20);
		sheet3.addMergedRegion(cra);
		Row row3 = sheet3.createRow(0);  
		row3.setHeight((short) 600);
		CellStyle style3 = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 16);
		style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直    
		style3.setAlignment(CellStyle.ALIGN_CENTER);// 水平   
        style3.setFont(font);
		Cell cell_1 = row3.createCell(0);
		cell_1.setCellStyle(style3);
		cell_1.setCellValue("When you're right , no one remembers, when you're wrong ,no one forgets .");  
        
        //cell 位置3-9被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。  
        Cell cell_2 = row3.createCell(10);  
          
        cell_2.setCellValue("what's up ! ");  
		wb.write(fos);  
		  
		fos.close();  

		
	}
	
}
