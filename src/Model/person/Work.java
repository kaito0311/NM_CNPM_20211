package Model.person;

import java.sql.Date;

public class Work {
    private int personID; 
    private String job; 
    private String place; 
    private Date modifiedDate; 
    public Work(int personID, String job, String place, Date modifiedDate){
        this.personID = personID; 
        this.job = job; 
        this.place = place; 
        this.modifiedDate = modifiedDate;

    }
    public Work(){
    }
    
    @Override
    public String toString() {
        return "Work [job=" + job + ", modifiedDate=" + modifiedDate + ", personID=" + personID + ", place=" + place
                + "]";
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    
}
