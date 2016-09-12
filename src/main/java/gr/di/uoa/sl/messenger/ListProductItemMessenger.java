package gr.di.uoa.sl.messenger;

public class ListProductItemMessenger {

	String id = null;
	String userId = null;
	String shopName = null;
	String shopId = null;
	String productName = null;
	String productId = null;
	boolean completed = false;
	boolean rated = false;
	int realValue = 0;
	
	public ListProductItemMessenger(String id, String userId, String shopName, String shopId, String productName, String productId, boolean completed, boolean rated, int realValue) {
		this.id = id;
		this.userId = userId;
		this.shopName = shopName;
		this.shopId = shopId;
		this.productName = productName;
		this.productId = productId;
		this.completed = completed;
		this.rated = rated;
		this.realValue = realValue;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isRated() {
		return rated;
	}
	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
	public int getRealValue() {
		return realValue;
	}
	public void setRealValue(int realValue) {
		this.realValue = realValue;
	}
	
}