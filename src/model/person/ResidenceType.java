package model.person;

public class ResidenceType {
	private int residenceTypeID;
	private String name;
	public ResidenceType(int residenceTypeID, String name) {
		super();
		this.residenceTypeID = residenceTypeID;
		this.name = name;
	}
	public ResidenceType() {}
	@Override
	public String toString() {
		return "ResidenceType [residenceTypeID=" + residenceTypeID + ", name=" + name + "]";
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
	
}
