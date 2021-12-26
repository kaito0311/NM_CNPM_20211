package thongke.dotuoi;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DoTuoi {
	private SimpleStringProperty doTuoi;
	private SimpleIntegerProperty soNK;
	private SimpleStringProperty tiLe;
	public DoTuoi(String doTuoi, int soNK, String tiLe) {
		super();
		this.doTuoi = new SimpleStringProperty(doTuoi);
		this.soNK = new SimpleIntegerProperty(soNK);
		this.tiLe = new SimpleStringProperty(tiLe);
	}
	public String getDoTuoi() {
		return doTuoi.get();
	}
	public int getSoNK() {
		return soNK.get();
	}
	public String getTiLe() {
		return tiLe.get();
	}
	
}
