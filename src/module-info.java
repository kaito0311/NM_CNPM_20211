module ChacLaCuoiCungCNPM {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires javafx.graphics;
	requires org.controlsfx.controls;
	requires java.desktop;
	requires javafx.swt;
	
	opens Main to javafx.graphics, javafx.fxml, javafx.base;
	opens MainChangeInfo to javafx.graphics, javafx.fxml, javafx.base;
	opens MainChangeKey to javafx.graphics, javafx.fxml, javafx.base;
	opens MainCreateListScholar to javafx.graphics, javafx.fxml, javafx.base;
	opens MainScholar to javafx.graphics, javafx.fxml, javafx.base;
	opens MainCreateListGift to javafx.graphics, javafx.fxml, javafx.base;
	opens MainGift to javafx.graphics, javafx.fxml, javafx.base;
	opens thongke.thongkehome to javafx.graphics, javafx.fxml;
	opens gift.thongkegift to javafx.graphics, javafx.fxml, javafx.base;
	opens gift.thongkescholar to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.changeperson.death to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.movehousehold.splithousehold to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.addnewperson to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.movehousehold.changeHead to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.createhouseholdbook to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.changeperson to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.movehousehold to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.changeperson.changeinformation to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau.deletehousehold;
	opens managehouseholdbook.thaydoisohokhau.canbenoneed to javafx.graphics, javafx.fxml, javafx.base;
	opens managehouseholdbook.thaydoisohokhau to javafx.graphics, javafx.fxml, javafx.base;
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens searchBook to javafx.graphics, javafx.fxml, javafx.base;
	opens searchPerson to javafx.graphics, javafx.fxml, javafx.base;	
	opens managehouseholdbook.tamtrutamvang to javafx.graphics, javafx.fxml, javafx.base;
	
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
