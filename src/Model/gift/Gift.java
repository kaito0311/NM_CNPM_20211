package model.gift;

public class Gift {
	private String giftID;
	private String event;
	public Gift(String giftID, String event) {
		super();
		this.giftID = giftID;
		this.event = event;
	}
	public String getGiftID() {
		return giftID;
	}
	public void setGiftID(String giftID) {
		this.giftID = giftID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
}
