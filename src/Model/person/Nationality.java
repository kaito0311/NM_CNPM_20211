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

    @Override
    public String toString() {
        return "Nationality [name=" + name + ", nationalityID=" + nationalityID + "]";
    }
    

    
}
