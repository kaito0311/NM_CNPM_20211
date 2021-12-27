package model.person;

public class Ethnic {
    private String ethnicID;
    private String name; 
    private String otherName;
    public Ethnic(String ethnicID, String name) {
        this.ethnicID = ethnicID;
        this.name = name;
    }
    public String getEthnicID() {
        return ethnicID;
    }
    public void setEthnicID(String ethnicID) {
        this.ethnicID = ethnicID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOtherName() {
        return otherName;
    }
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
    @Override
    public String toString() {
        return "Ethnic [ethnicID=" + ethnicID + ", name=" + name + "]";
    } 
    
}