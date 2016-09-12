package gr.di.uoa.sl.messenger;

public class NewProductToList {

	private String userToken = null;
	private String productId = null;
	
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}