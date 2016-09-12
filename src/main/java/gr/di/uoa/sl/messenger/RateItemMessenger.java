package gr.di.uoa.sl.messenger;

public class RateItemMessenger {
	String shopId = null;
	String comment = null;
	String productId = null;
	String userToken = null;
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment= comment;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
