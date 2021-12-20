package Model.person;

public class Nationality {
    private String nationalityID; 
    private String name; 

    public Nationality(String nationalityID, String name){
        this.nationalityID = nationalityID;
        this.name = name; 
    }

    public Nationality(){

    }

    public void printDetail(){
        System.out.println(this.nationalityID + " " + this.name);
    }

    
}
