package thongke;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.person.InforPerson;
import thongke.dotuoi.DoTuoiController;

public class PersonDetailController implements Initializable {
	
	public static InforPerson nhanKhauSelected;
	@FXML
	private TextField maNK;
	@FXML
	private TextField ten;
	@FXML
	private TextField biDanh;
	@FXML
	private TextField ngaySinh;
	@FXML
	private TextField hocVan;
	@FXML
	private TextField congViec;
	@FXML
	private TextField noiLamViec;
	@FXML
	private TextField danToc;
	@FXML
	private TextField noiSinh;
	@FXML
	private TextField que;
	@FXML
	private TextField CCCD;
	@FXML
	private TextField ngayCap;
	@FXML
	private TextField noiCap;
	@FXML
	private TextField quocTich;
	@FXML
	private TextField ngayDKTTru;
	@FXML
	private TextField DCTTruTruocDay;
	@FXML
	private TextField hoKhau;
	@FXML
	private TextField diaChi;
	@FXML
	private TextField chuHo;	
	@FXML
	private TextField quanHevsChuHo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showDetail();
	}
	
	public void showDetail() {
		SQLConnection.ConnectData();
		InforPerson nk = nhanKhauSelected;
		System.out.println(nk.getCard().getRegisterDate());
		
		maNK.setEditable(false);
		maNK.setText(String.valueOf(nk.getPerson().getPersonID()));
		ten.setEditable(false);
		ten.setText(nk.getPerson().getFullName());
		biDanh.setEditable(false);
		biDanh.setText(nk.getPerson().getNickName());
		ngaySinh.setEditable(false);
		ngaySinh.setText(nk.getPerson().getBirthDate().toString());
		hocVan.setEditable(false);
		hocVan.setText(nk.getAcademicLevel().getName());
		congViec.setEditable(false);
		congViec.setText(nk.getWork().getJob());
		noiLamViec.setEditable(false);
		noiLamViec.setText(nk.getWork().getPlace());
		danToc.setEditable(false);
		danToc.setText(nk.getEthnic().getName());
		noiSinh.setEditable(false);
		noiSinh.setText(SQLConnection.toPlace(nk.getBirthPlace().getNationID(), nk.getBirthPlace().getProvinceID(), nk.getBirthPlace().getDistrictID(), nk.getBirthPlace().getCommuneID()));
		que.setEditable(false);
		que.setText(SQLConnection.toPlace(nk.getOriginPlace().getNationID(), nk.getOriginPlace().getProvinceID(), nk.getOriginPlace().getDistrictID(), nk.getOriginPlace().getCommuneID()));
		CCCD.setEditable(false);
		CCCD.setText(nk.getCard().getNumber());
		ngayCap.setEditable(false);
		ngayCap.setText(String.valueOf(nk.getCard().getRegisterDate()));
		noiCap.setEditable(false);
		noiCap.setText(nk.getCard().getRegisterPlace());
		quocTich.setEditable(false);
		quocTich.setText(nk.getNationality().getName());
		ngayDKTTru.setEditable(false);
		ngayDKTTru.setText(nk.getResidence().getPrepernamentAddress());
		DCTTruTruocDay.setEditable(false);
		DCTTruTruocDay.setText(nk.getResidence().getPrepernamentAddress());
		hoKhau.setEditable(false);
		hoKhau.setText(String.valueOf(nk.getResidence().getBookID()));
		diaChi.setEditable(false);
		diaChi.setText(SQLConnection.toCommune(nk.getOriginPlace().getCommuneID()));
		chuHo.setEditable(false);
		chuHo.setText(nk.getPerson().getFullName());
		quanHevsChuHo.setEditable(false);
		quanHevsChuHo.setText(nk.getResidence().getRelaWithHead());
		try {

			SQLConnection.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
