package model.person;

import java.sql.Date;
import java.time.LocalDate;

public class Person {
    private int personID;
    private String fullName ; 
    private String nickName; 
    private Date birthDate; 
    private int gender; 
    private String ethnicID; 
    private String nationalityID;
    public Person(int personID, String fullName, String nickName, Date birthDate, int gender, String ethnicID, String nationalityID){
        this.personID = personID; 
        this.fullName = fullName; 
        this.nickName = nickName; 
        this.birthDate = birthDate; 
        this.gender = gender;
        this.ethnicID = ethnicID; 
        this.nationalityID = nationalityID;
    }
    public Person(){}
    
    @Override
    public String toString() {
        return "Person [birthDate=" + birthDate + ", ethnicID=" + ethnicID + ", fullName=" + fullName + ", gender="
                + gender + ", nationalityID=" + nationalityID + ", nickName=" + nickName + ", personID=" + personID
                + "]";
    }
    public int getPersonID() {
        return personID;
    }
    public String getNationalityID() {
        return nationalityID;
    }
    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }
    public String getEthnicID() {
        return ethnicID;
    }
    public void setEthnicID(String ethnicID) {
        this.ethnicID = ethnicID;
    }
    public String getGender() {
		if (gender == 1)
			return "Nam";
		return "Nữ";
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public LocalDate getBirthDate() {
        return birthDate.toLocalDate();
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    } 
    public int getAge() {
    	return LocalDate.now().getYear() - this.getBirthDate().getYear();
    }
    
}