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

    public void printDetail(){
        System.out.println(personID + " "+ job + " " + place + " " + modifiedDate);
    }
}
