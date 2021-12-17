module QuanLiHoKhau_CNPM {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports dotuoi;
	opens dotuoi to javafx.graphics, javafx.fxml;
	
	exports gioitinh;
	opens gioitinh to javafx.graphics, javafx.fxml;
	
	exports tamvang_tamtru;
	opens tamvang_tamtru to javafx.graphics, javafx.fxml;
	
	exports thoigian;
	opens thoigian to javafx.graphics, javafx.fxml;
	

}
