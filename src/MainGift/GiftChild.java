package MainGift;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class GiftChild {
	
	private ComboBox<String> giftName;
    private final SimpleStringProperty age;
    
    GiftChild(String age, ObservableList<String> dataGift) {
    	this.giftName = new ComboBox<String>(dataGift);
    	this.age = new SimpleStringProperty(age);
    }
    
	public ComboBox<String> getGiftName() {
		return giftName;
	}
    public void setGiftName(ComboBox<String> giftName) {
        this.giftName = giftName;
    }

    public String getAge() {
        return age.get();
    }
    public void setAge(String age) {
        this.age.set(age); 
    }

}
