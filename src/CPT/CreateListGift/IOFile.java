package CPT.CreateListGift;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IOFile {
	
	public static void writeExcel(ArrayList<Person> listPerson) throws IOException {
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet workSheet = workbook.createSheet("name");
		XSSFRow row;
		XSSFCell cellA, cellB, cellC, cellD, cellE, cellF;
		workSheet.setColumnWidth(0, 2000);
		workSheet.setColumnWidth(1, 7000);
		workSheet.setColumnWidth(2, 5000);
		
		

		
		row = workSheet.createRow(0);
		cellA = row.createCell(0);
		cellA.setCellValue("STT");
		cellB = row.createCell(1);
		cellB.setCellValue("Họ tên");
		cellC = row.createCell(2);
		cellC.setCellValue("Ngày sinh");
		cellD = row.createCell(3);
		cellD.setCellValue("Giới tính");
		cellE = row.createCell(4);
		cellE.setCellValue("Tuổi");
		cellF = row.createCell(5);
		cellF.setCellValue("Mã hộ khẩu");
		
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
			cellD.setCellValue(p.getGender());
			cellE = row.createCell(4);
			cellE.setCellValue(p.getAge());
			cellF = row.createCell(5);
			cellF.setCellValue(p.getHouseID());
			i++;
		}
		
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    FileOutputStream file = new FileOutputStream(fileToSave.getAbsolutePath());
		    workbook.write(file);
			workbook.close();
			file.close();
		}
		
		
		
	}
	
//	public static void main(String[] args) throws IOException {
//		readExcel();
//		writeExcel(5, "Trần Kim Quốc Thắng", "1/6/2001", "Bách Khoa", "6A", "Học sinh giỏi", "1234");
//	}
	
	
}