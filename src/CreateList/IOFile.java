package CreateList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IOFile {
	
	public static void readExcel() throws IOException {
		FileInputStream file = new FileInputStream("src/CreateList/Read.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
//		for (Row row:sheet) {
//			for (Cell cell:row) {
//				switch (formula.evaluate(cell).getCellType()) {
//				case STRING:
//					System.out.println(cell.getStringCellValue());
//					break;
//				case NUMERIC:
//					System.out.println(cell.getNumericCellValue());
//					break;
//				}
//			}
//			System.out.println();
//		}
		for (Row row:sheet) {
			if (row.getCell(0)!=null) {
				System.out.println(row.getCell(0));
			}
			if (row.getCell(1)!=null) {
				System.out.println(row.getCell(1));
			}
		}
	}
	
	public static void writeExcel(ArrayList<Person> listPerson) throws IOException {
		FileOutputStream file = new FileOutputStream("src/CreateList/Write.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet workSheet = workbook.createSheet("name");
		XSSFRow row;
		XSSFCell cellA, cellB, cellC, cellD, cellE, cellF, cellG;
		
		row = workSheet.createRow(0);
		cellA = row.createCell(0);
		cellA.setCellValue("STT");
		cellB = row.createCell(1);
		cellB.setCellValue("Họ tên");
		cellC = row.createCell(2);
		cellC.setCellValue("Ngày sinh");
		cellD = row.createCell(3);
		cellD.setCellValue("Trường");
		cellE = row.createCell(4);
		cellE.setCellValue("Lớp");
		cellF = row.createCell(5);
		cellF.setCellValue("Thành tích học tập");
		cellG = row.createCell(6);
		cellG.setCellValue("Mã hộ khẩu");
		
		int i = 1;
		for (Person p:listPerson) {
			row = workSheet.createRow(i);
			cellA = row.createCell(0);
			cellA.setCellValue(i);
			cellB = row.createCell(1);
			cellB.setCellValue(p.getFullName());
			cellC = row.createCell(2);
			cellC.setCellValue(p.getBirthDate());
			cellD = row.createCell(3);
			cellD.setCellValue(p.getSchool());
			cellE = row.createCell(4);
			cellE.setCellValue(p.getClassName());
			cellF = row.createCell(5);
			cellF.setCellValue(p.getAchievement().getValue().toString());
			cellG = row.createCell(6);
			cellG.setCellValue(p.getHouseID());
			i++;
		}
		
		
		
		workbook.write(file);
		workbook.close();
		file.close();
		
	}
	
//	public static void main(String[] args) throws IOException {
//		readExcel();
//		writeExcel(5, "Trần Kim Quốc Thắng", "1/6/2001", "Bách Khoa", "6A", "Học sinh giỏi", "1234");
//	}
	
	
}