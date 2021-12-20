package Model.person;

public class BirthPlace {
    private int personID; 
    private String nationID; 
    private String provinceID;
    private String districtID; 
    private String communeID; 
    
    public BirthPlace(int personID, String nationID, String provinceID, String districtID, String communeID ){
        this.personID = personID; 
        this.nationID = nationID; 
        this.provinceID = provinceID; 
        this.districtID = districtID; 
        this.communeID = communeID; 
    }

    public BirthPlace(){}
    
    public void printDetail(){
        System.out.println(personID + " " + nationID + " " + provinceID + " " + districtID + " " + communeID);
    }
}
