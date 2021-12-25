package managehouseholdbook.thaydoisohokhau.movehousehold.splithousehold;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ChangeMember {
    String personID;
    String namePerson;

    String relationshipWithHead; 
    ComboBox<String> idHoKhau;
    public ChangeMember(String personID, String namePerson, String relatioinshipWithHead,
    ObservableList<String> idHoKhau) {
        this.personID = personID;
        this.namePerson = namePerson;
        this.relationshipWithHead = relatioinshipWithHead;
        this.idHoKhau = new ComboBox<String>(idHoKhau);
    }
    public String getRelationshipWithHead() {
        return relationshipWithHead;
    }
    public void setRelationshipWithHead(String relationshipWithHead) {
        this.relationshipWithHead = relationshipWithHead;
    }
    public ChangeMember(String personID, String namePerson, String relatioinshipWithHead, ComboBox<String> idHoKhau) {
        this.personID = personID;
        this.namePerson = namePerson;
        this.relationshipWithHead = relatioinshipWithHead;
        this.idHoKhau = idHoKhau;
    }
    public String getPersonID() {
        return personID;
    }
    public void setPersonID(String personID) {
        this.personID = personID;
    }
    public String getNamePerson() {
        return namePerson;
    }
    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public ComboBox<String> getIdHoKhau() {
        return idHoKhau;
    }
    public void setIdHoKhau(ComboBox<String> idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
    
}
