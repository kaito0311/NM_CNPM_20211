package model.person;

import database.SQLConnection;

public class OriginPlace {
    private int personID; 
    private String nationID;
    private String provinceID; 
    private String districtID; 
    private String communeID;
	private String address; 

    public OriginPlace(int personID, String nationID, String provinceID, String districtID, String communeID ){
        this.personID = personID; 
        this.nationID = nationID; 
        this.provinceID = provinceID; 
        this.districtID = districtID; 
        this.communeID = communeID; 
        SQLConnection.ConnectData();
        this.address = SQLConnection.toPlace(nationID, provinceID, districtID, communeID);
        SQLConnection.DisconnectData();
    }

    public OriginPlace(){}

    @Override
    public String toString() {
        return "OriginPlace [communeID=" + communeID + ", districtID=" + districtID + ", nationID=" + nationID
                + ", personID=" + personID + ", provinceID=" + provinceID + "]";
    }

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getNationID() {
		return nationID;
	}

	public void setNationID(String nationID) {
		this.nationID = nationID;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getCommuneID() {
		return communeID;
	}

	public void setCommuneID(String communeID) {
		this.communeID = communeID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}