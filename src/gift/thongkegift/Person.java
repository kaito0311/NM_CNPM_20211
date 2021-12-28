package gift.thongkegift;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty event;
    private final SimpleStringProperty gift;
    private final SimpleIntegerProperty value;   
    private final SimpleStringProperty houseID;
    private final SimpleIntegerProperty year;
    
 
    public Person(String fullName, String birthDate, String event, String houseID, String gift, int value, int year) {
        this.fullName = new SimpleStringProperty(fullName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.event = new SimpleStringProperty(event);
		this.gift = new SimpleStringProperty(gift);
		this.value = new SimpleIntegerProperty (value);
        this.houseID = new SimpleStringProperty(houseID);
		this.year = new SimpleIntegerProperty (year);
        
    }
 
    public int getYear() {
		return year.get();
	}

	public String getGift() {
		return gift.get();
	}

	public int getValue() {
		return value.get();
	}

	public String getFullName() {
        return fullName.get();
    }
    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
        
    public String getBirthDate() {
        return birthDate.get();
    }
    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }
    
    public String getEvent() {
        return event.get();
    }
    
    public String getHouseID() {
        return houseID.get();
    }
    public void setHouseID(String houseID) {
        this.houseID.set(houseID);   
    }
}
