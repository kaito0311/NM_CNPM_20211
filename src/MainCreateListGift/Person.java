/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainCreateListGift;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Person {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty gender;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty houseID;
    
 
     Person(String fullName, String birthDate, String gender, int age, String houseID) {
        this.fullName = new SimpleStringProperty(fullName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.houseID = new SimpleStringProperty(houseID);
        
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
