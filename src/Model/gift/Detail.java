package model.gift;

public class Detail {
	private String giftID;
	private int productID;
	private int quantity;
	public Detail(String giftID, int productID, int quantity) {
		super();
		this.giftID = giftID;
		this.productID = productID;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Detail [giftID=" + giftID + ", productID=" + productID + ", quantity=" + quantity + "]";
	}
	public String getGiftID() {
		return giftID;
	}
	public void setGiftID(String giftID) {
		this.giftID = giftID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
