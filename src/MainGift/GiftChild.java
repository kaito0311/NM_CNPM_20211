package MainGift;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class GiftChild {
	
	private ComboBox<String> giftName;
	private final SimpleIntegerProperty price;
    private final SimpleStringProperty age;
    
    GiftChild(String age, ObservableList<String> dataGift, int price) {
    	this.giftName = new ComboBox<String>(dataGift);
    	this.price = new SimpleIntegerProperty(price);
    	this.age = new SimpleStringProperty(age);
    }
    
	public ComboBox<String> getGiftName() {
		return giftName;
	}
    public void setGiftName(ComboBox<String> giftName) {
        this.giftName = giftName;
    }
	public int getPrice() {
        return price.get();
    }
    public void setPrice(int price) {
        this.price.set(price);
    }
    public String getAge() {
        return age.get();
    }
    public void setAge(String age) {
        this.age.set(age); 
    }

}
