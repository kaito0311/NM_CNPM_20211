package Model.person;

import java.util.Date;

public class IdentityCard {
	
	private int personID;
	private String number;
	private Date registerDate;
	private String registerPlace;
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterPlace() {
		return registerPlace;
	}
	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}
	
	

}
