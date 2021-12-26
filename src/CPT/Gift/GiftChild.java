package CPT.Gift;

import javafx.beans.property.SimpleStringProperty;

public class GiftChild {
	
	private final SimpleStringProperty giftName;
	private final SimpleStringProperty price;
    private final SimpleStringProperty quantity;
    
    GiftChild(String giftName, String price,  String quantity) {
    	this.giftName = new SimpleStringProperty(giftName);
    	this.price = new SimpleStringProperty(price);
    	this.quantity = new SimpleStringProperty(quantity);
    }
    
    public String getGiftName() {
        return giftName.get();
    }
    public void setGiftName(String giftName) {
        this.giftName.set(giftName);
    }
    public String getPrice() {
        return price.get();
    }
    public void setPrice(String price) {
        this.price.set(price);
    }
    public String getQuantity() {
        return quantity.get();
    }
    public void setQuantity(String quantity) {
        this.quantity.set(quantity); 
    }

}
