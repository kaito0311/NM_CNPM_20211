package Model.person;

public class OriginPlace {
    private int personID; 
    private String nationID;
    private String provinceID; 
    private String districtID; 
    private String communeID; 

    public OriginPlace(int personID, String nationID, String provinceID, String districtID, String communeID ){
        this.personID = personID; 
        this.nationID = nationID; 
        this.provinceID = provinceID; 
        this.districtID = districtID; 
        this.communeID = communeID; 
    }

    public OriginPlace(){}

    @Override
    public String toString() {
        return "OriginPlace [communeID=" + communeID + ", districtID=" + districtID + ", nationID=" + nationID
                + ", personID=" + personID + ", provinceID=" + provinceID + "]";
    }
    
}
