module QLHK_CNPM_2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	requires java.sql;
	
	opens thongke.thongkehome to javafx.graphics, javafx.fxml;
	
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
