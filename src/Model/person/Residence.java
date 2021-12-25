package Model.person;

public class Residence {
    private int personID;
    private int residenceTypeID;
    private String prePermanentAddress;
    private int bookID;
    private String relationshipWithHead;

    
    public Residence() {
    }
    public Residence(int personID, int residenceTypeID, String prePermanentAddress, int bookID,
            String relationshipWithHead) {
        this.personID = personID;
        this.residenceTypeID = residenceTypeID;
        this.prePermanentAddress = prePermanentAddress;
        this.bookID = bookID;
        this.relationshipWithHead = relationshipWithHead;
    }
    @Override
    public String toString() {
        return "Residence [bookID=" + bookID + ", personID=" + personID + ", prePermanentAddress=" + prePermanentAddress
                + ", relationshipWithHead=" + relationshipWithHead + ", residenceTypeID=" + residenceTypeID + "]";
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public int getResidenceTypeID() {
        return residenceTypeID;
    }
    public void setResidenceTypeID(int residenceTypeID) {
        this.residenceTypeID = residenceTypeID;
    }
    public String getPrePermanentAddress() {
        return prePermanentAddress;
    }
    public void setPrePermanentAddress(String prePermanentAddress) {
        this.prePermanentAddress = prePermanentAddress;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getRelationshipWithHead() {
        return relationshipWithHead;
    }
    public void setRelationshipWithHead(String relationshipWithHead) {
        this.relationshipWithHead = relationshipWithHead;
    }
    
}
