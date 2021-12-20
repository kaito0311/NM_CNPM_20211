package Model.person;

import java.sql.Date;

public class IdentityCard {
    private int personID; 
    private String number; 
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

    private Date registerDate; 
    private String registerPlace; 

    public IdentityCard(int personID, String number, Date registerDate, String registerPlace){
        this.personID = personID; 
        this.number = number;
        this.registerDate = registerDate;
        this.registerPlace = registerPlace;
    }

    public IdentityCard(){}

    @Override
    public String toString() {
        return "IdentityCard [number=" + number + ", personID=" + personID + ", registerDate=" + registerDate
                + ", registerPlace=" + registerPlace + "]";
    } 
    
}
