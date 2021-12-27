package model.person;

public class Residence {
	private int personID;
	private int residencetypeID;
	private String prepernamentAddress;
	private int bookID;
	private String relaWithHead;
	
	public Residence(int personID, int residencetypeID, String prepernamentAddress, int bookID, String relaWithHead) {
		super();
		this.personID = personID;
		this.residencetypeID = residencetypeID;
		this.prepernamentAddress = prepernamentAddress;
		this.bookID = bookID;
		this.relaWithHead = relaWithHead;
	}
	
	public Residence() {}

	@Override
	public String toString() {
		return "Residence [personID=" + personID + ", residencetypeID=" + residencetypeID + ", prepernamentAddress="
				+ prepernamentAddress + ", bookID=" + bookID + ", relaWithHead=" + relaWithHead + "]";
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getResidencetypeID() {
		return residencetypeID;
	}

	public void setResidencetypeID(int residencetypeID) {
		this.residencetypeID = residencetypeID;
	}

	public String getPrepernamentAddress() {
		return prepernamentAddress;
	}

	public void setPrepernamentAddress(String prepernamentAddress) {
		this.prepernamentAddress = prepernamentAddress;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getRelaWithHead() {
		return relaWithHead;
	}

	public void setRelaWithHead(String relaWithHead) {
		this.relaWithHead = relaWithHead;
	}
	
}
