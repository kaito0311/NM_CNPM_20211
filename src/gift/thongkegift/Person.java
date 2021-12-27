package gift.thongkegift;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty gender;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty gift;
    private final SimpleStringProperty value;   
    private final SimpleStringProperty houseID;
    
 
    public Person(String fullName, String birthDate, String gender, int age, String houseID, String gift, String value) {
        this.fullName = new SimpleStringProperty(fullName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
		this.gift = new SimpleStringProperty(gift);
		this.value = new SimpleStringProperty (value);
        this.houseID = new SimpleStringProperty(houseID);
        
    }
 
    public SimpleStringProperty getGift() {
		return gift;
	}

	public SimpleStringProperty getValue() {
		return value;
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
    
    public String getGender() {
        if (gender.get().equals("1"))
        	return "Nam";
        
        return "Nữ";
    }
    public void setGender(String school) {
        this.gender.set(school);
    }
    
    public int getAge() {
        return age.get();
    }
    public void setAge(int className) {
        this.age.set(className);
    }
    
    public String getHouseID() {
        return houseID.get();
    }
    public void setHouseID(String houseID) {
        this.houseID.set(houseID);   
    }
}