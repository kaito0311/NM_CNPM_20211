package model.person;

public class Education {
	private int personID;
	private int academicLevel;
	private String primarySchool;
	private String juniorHighSchool;
	private String highSchool;
	private String clas;
	public Education(int personID, int academicLevel, String primarySchool, String juniorHighSchool, String highSchool,
			String klass) {
		super();
		this.personID = personID;
		this.academicLevel = academicLevel;
		this.primarySchool = primarySchool;
		this.juniorHighSchool = juniorHighSchool;
		this.highSchool = highSchool;
		this.clas = klass;
	}
	@Override
	public String toString() {
		return "Education [personID=" + personID + ", academicLevel=" + academicLevel + ", primarySchool="
				+ primarySchool + ", juniorHighSchool=" + juniorHighSchool + ", highSchool=" + highSchool + ", klass="
				+ clas + "]";
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getAcademicLevel() {
		return academicLevel;
	}
	public void setAcademicLevel(int academicLevel) {
		this.academicLevel = academicLevel;
	}
	public String getPrimarySchool() {
		return primarySchool;
	}
	public void setPrimarySchool(String primarySchool) {
		this.primarySchool = primarySchool;
	}
	public String getJuniorHighSchool() {
		return juniorHighSchool;
	}
	public void setJuniorHighSchool(String juniorHighSchool) {
		this.juniorHighSchool = juniorHighSchool;
	}
	public String getHighSchool() {
		return highSchool;
	}
	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}
	public String getClas() {
		return clas;
	}
	public void setClass(String klass) {
		this.clas = klass;
	}
	


}
