package Model.person;

public class Ethnic {
    private String ethnicID;
    private String name; 
    private String otherName; 
    
    public Ethnic(String ethnicID, String name){
        this.ethnicID = ethnicID; 
        this.name = name;
    }

    public Ethnic(){}

    public void printDetail(){
        System.out.println(this.ethnicID + " " + this.name);
    }
}
