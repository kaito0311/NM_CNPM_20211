module NMCNPM {
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires org.apache.poi.ooxml;
	
	opens Login to javafx.graphics, javafx.fxml, javafx.base;
	opens QLTK.ChangeInfo to javafx.graphics, javafx.fxml, javafx.base;
	opens QLTK.ChangeKey to javafx.graphics, javafx.fxml, javafx.base;
	opens CPT.CreateListGift to javafx.graphics, javafx.fxml, javafx.base;
	opens CPT.CreateListScholar to javafx.graphics, javafx.fxml, javafx.base;
	opens CPT.Gift to javafx.graphics, javafx.fxml, javafx.base;
	opens CPT.Scholar to javafx.graphics, javafx.fxml, javafx.base;
}