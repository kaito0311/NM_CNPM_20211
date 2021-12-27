package model.gift;

public class Giving {
	private int givingID;
	private int personID;
	private String giftID;
	private int year;
	public Giving(int givingID, int personID, String giftID, int year) {
		super();
		this.givingID = givingID;
		this.personID = personID;
		this.giftID = giftID;
		this.year = year;
	}
	public int getGivingID() {
		return givingID;
	}
	public void setGivingID(int givingID) {
		this.givingID = givingID;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getGiftID() {
		return giftID;
	}
	public void setGiftID(String giftID) {
		this.giftID = giftID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Giving [givingID=" + givingID + ", personID=" + personID + ", giftID=" + giftID + ", year=" + year
				+ "]";
	}

	
}
