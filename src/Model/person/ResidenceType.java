package Model.person;

public class ResidenceType {
    private int residenceTypeID;
    private String name;

    public ResidenceType(int residenceTypeID, String name) {
        this.residenceTypeID = residenceTypeID;
        this.name = name;
    }

    public int getResidenceTypeID() {
        return residenceTypeID;
    }

    public void setResidenceTypeID(int residenceTypeID) {
        this.residenceTypeID = residenceTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResidenceType [name=" + name + ", residenceTypeID=" + residenceTypeID + "]";
    }

}
