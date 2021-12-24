package model.person;

import java.sql.Date;

public class TemporaryAbsence  {
    private int certificatedID;
    private int personID; 
    private String tempResidencePlace; 
    private Date fromDate; 
    private Date toDate; 
    private Date certifiedDate;
    
	public TemporaryAbsence(int certificatedID, int personID, String tempResidencePlace, Date fromDate, Date toDate,
			Date certifiedDate) {
		super();
		this.certificatedID = certificatedID;
		this.personID = personID;
		this.tempResidencePlace = tempResidencePlace;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.certifiedDate = certifiedDate;
	}

	@Override
	public String toString() {
		return "TemporaryAbsence [certificatedID=" + certificatedID + ", personID=" + personID + ", tempResidencePlace="
				+ tempResidencePlace + ", fromDate=" + fromDate + ", toDate=" + toDate + ", certifiedDate="
				+ certifiedDate + "]";
	}

	public int getCertificatedID() {
		return certificatedID;
	}

	public void setCertificatedID(int certificatedID) {
		this.certificatedID = certificatedID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getTempResidencePlace() {
		return tempResidencePlace;
	}

	public void setTempResidencePlace(String tempResidencePlace) {
		this.tempResidencePlace = tempResidencePlace;
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

	public Date getCertifiedDate() {
		return certifiedDate;
	}

	public void setCertifiedDate(Date certifiedDate) {
		this.certifiedDate = certifiedDate;
	} 

    
	
}
