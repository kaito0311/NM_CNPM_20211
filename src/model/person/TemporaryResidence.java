package model.person;

import java.sql.Date;

public class TemporaryResidence {
    private int certificateID;
    private int personID; 
    private Date fromDate; 
    private Date toDate; 
    private String provinceID;
    private String districtID; 
    private String communeID;
    private String detailAddress; 
    private Date certifiedDate;
    
	public TemporaryResidence(int certificateID, int personID, Date fromDate, Date toDate, String provinceID,
			String districtID, String communeID, String detailAddress, Date certifiedDate) {
		super();
		this.certificateID = certificateID;
		this.personID = personID;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.provinceID = provinceID;
		this.districtID = districtID;
		this.communeID = communeID;
		this.detailAddress = detailAddress;
		this.certifiedDate = certifiedDate;
	}

	@Override
	public String toString() {
		return "TemporaryResidence [certificateID=" + certificateID + ", personID=" + personID + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", provinceID=" + provinceID + ", districtID=" + districtID
				+ ", communeID=" + communeID + ", detailAddress=" + detailAddress + ", certifiedDate=" + certifiedDate
				+ "]";
	}

	public int getCertificateID() {
		return certificateID;
	}

	public void setCertificateID(int certificateID) {
		this.certificateID = certificateID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Date getCertifiedDate() {
		return certifiedDate;
	}

	public void setCertifiedDate(Date certifiedDate) {
		this.certifiedDate = certifiedDate;
	} 
    
    
    
}