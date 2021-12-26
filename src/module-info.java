module CapPhanThuong {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires javafx.graphics;
	requires org.controlsfx.controls;
	
	opens Main to javafx.graphics, javafx.fxml, javafx.base;
	opens MainChangeInfo to javafx.graphics, javafx.fxml, javafx.base;
	opens MainChangeKey to javafx.graphics, javafx.fxml, javafx.base;
	opens MainCreateListScholar to javafx.graphics, javafx.fxml, javafx.base;
	opens MainScholar to javafx.graphics, javafx.fxml, javafx.base;
	opens MainCreateListGift to javafx.graphics, javafx.fxml, javafx.base;
	opens MainGift to javafx.graphics, javafx.fxml, javafx.base;
	opens thongke.thongkehome to javafx.graphics, javafx.fxml;
	opens gift.thongkegift to javafx.graphics, javafx.fxml;
	
	exports thongke.dotuoi;
	opens thongke.dotuoi to javafx.graphics, javafx.fxml;
	
	exports thongke;
	opens thongke to javafx.graphics, javafx.fxml;
	
	exports thongke.gioitinh;
	opens thongke.gioitinh to javafx.graphics, javafx.fxml;
	
	exports thongke.tamvang_tamtru;
	opens thongke.tamvang_tamtru to javafx.graphics, javafx.fxml;
	
	exports thongke.thoigian;
	opens thongke.thoigian to javafx.graphics, javafx.fxml;
}