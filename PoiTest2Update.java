package test_;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiTest2Update {

	public static void main(String[] args) throws Exception {
		// 创建 Excel 文件的输入流对象
		FileInputStream excelFileInputStream = new FileInputStream("G:/ts.xlsx");
		// XSSFWorkbook 就代表一个 Excel 文件
		// 创建其对象，就打开这个 Excel 文件
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
		// 输入流使用后，及时关闭！这是文件流操作中极好的一个习惯！
		excelFileInputStream.close();
		// XSSFSheet 代表 Excel 文件中的一张表格
		// 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
		// 注意表格索引从 0 开始！
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 开始循环表格数据,表格的行索引从 0 开始

		// employees.xlsx 第一行是标题行，我们从第二行开始, 对应的行索引是 1

		// sheet.getLastRowNum() : 获取当前表格中最后一行数据对应的行索引

//		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//			// XSSFRow 代表一行数据
//			XSSFRow row = sheet.getRow(rowIndex);
//			if (row == null) {
//			continue;
//			}
//			XSSFCell nameCell = row.getCell(0); // 姓名列
//			XSSFCell genderCell = row.getCell(1); // 性别列
//			XSSFCell ageCell = row.getCell(2); // 年龄列
//			XSSFCell weightCell = row.getCell(3); // 体重列
////			XSSFCell salaryCell = row.getCell(4); // 收入列
//			XSSFCell salaryCell = row.createCell(4,CellType.NUMERIC);
//			salaryCell.setCellValue(10000);
//			StringBuilder employeeInfoBuilder = new StringBuilder();
////			employeeInfoBuilder.append("员工信息 --> ")
////			.append("姓名 : ").append(nameCell.getStringCellValue())
////			.append(" , 性别 : ").append(genderCell.getStringCellValue())
////			.append(" , 年龄 : ").append(ageCell.getNumericCellValue())
////			.append(" , 体重(千克) : ").append(weightCell.getNumericCellValue())
////			.append(" , 月收入(元) : ").append(salaryCell.getNumericCellValue());
//			System.out.println(employeeInfoBuilder.toString());
//		}
//		int currentLastRowIndex = sheet.getLastRowNum();
//
//		int newRowIndex = currentLastRowIndex + 1;

//		XSSFRow newRow = sheet.createRow(newRowIndex);

		// 开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始

//		int cellIndex = 0;

		// 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同

//		XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
//		newNameCell.setCellValue("钱七");
//		XSSFCell newGenderCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
//		newGenderCell.setCellValue("女");
//		XSSFCell newAgeCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
//		newAgeCell.setCellValue(50);
//		XSSFCell newWeightCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
//		newWeightCell.setCellValue(68);
//		XSSFCell newSalaryCell = newRow.createCell(cellIndex++);
//		newSalaryCell.s(6000);
		// 将最新的 Excel 文件写入到文件输出流中，更新文件信息！
		XSSFDrawing patriarch = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = new XSSFClientAnchor(200, 200, 200, 200,(short) 4, 4, (short) 5, 5);
		XSSFClientAnchor anchor1 = new XSSFClientAnchor(0, 0, 255, 255,(short) 1, 10, (short) 2, 11);
		anchor.setAnchorType(3); 
		//插入图片    
		BufferedImage bufferImg = null;
		BufferedImage bufferImg1 = null;
		// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();
		bufferImg = ImageIO.read(new File("g:/icon.png"));
		bufferImg1 = ImageIO.read(new File("g:/2.jpg"));
		ImageIO.write(bufferImg, "jpg", byteArrayOut);
		ImageIO.write(bufferImg1, "jpg", byteArrayOut1);
        
        patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG)).resize();
        patriarch.createPicture(anchor1, workbook.addPicture(byteArrayOut1.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
		FileOutputStream excelFileOutPutStream = new FileOutputStream("G:/ts1.xlsx");
		workbook.write(excelFileOutPutStream);
		 // 执行 flush 操作， 将缓存区内的信息更新到文件上
		excelFileOutPutStream.flush();
		// 使用后，及时关闭这个输出流对象， 好习惯，再强调一遍！

		excelFileOutPutStream.close();
	}
}
