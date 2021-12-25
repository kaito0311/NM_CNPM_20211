/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainCreateListScholar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Person {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty school;
    private final SimpleStringProperty className;
    private final SimpleStringProperty houseID;
    private ComboBox<String> achievement;    
    
 
     public Person(String fullName, String birthDate, String school, String className, ObservableList<String> data, String houseID) {
        this.fullName = new SimpleStringProperty(fullName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.school = new SimpleStringProperty(school);
        this.className = new SimpleStringProperty(className);
        this.houseID = new SimpleStringProperty(houseID);
        this.achievement = new ComboBox<String>(data);         
        
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
    
    public String getSchool() {
        return school.get();
    }
    public void setSchool(String school) {
        this.school.set(school);
    }
    
    public String getClassName() {
        return className.get();
    }
    public void setClassName(String className) {
        this.className.set(className);
    }
    
    public String getHouseID() {
        return houseID.get();
    }
    public void setHouseID(String houseID) {
        this.houseID.set(houseID);   
    }
    

    public ComboBox<String> getAchievement() {
        return achievement;
    }
 
    public void setAchievement(ComboBox<String> achievement) {
        this.achievement = achievement;
    }
    
    
    
}
