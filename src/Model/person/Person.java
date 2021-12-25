package Model.person;

import java.sql.Date;

/**
 * @author Dell
 *
 */
public class Person {
    private int personID;
    private String fullName ; 
    private String nickName; 
    private Date birthDate; 
    private int gender; 
    private String ethnicID; 
    private String nationalityID;
    
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEthnicID() {
		return ethnicID;
	}
	public void setEthnicID(String ethnicID) {
		this.ethnicID = ethnicID;
	}
	public String getNationalityID() {
		return nationalityID;
	}
	public void setNationalityID(String nationalityID) {
		this.nationalityID = nationalityID;
	} 
    
    
}
