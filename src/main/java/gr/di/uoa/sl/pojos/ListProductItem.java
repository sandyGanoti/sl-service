package gr.di.uoa.sl.pojos;

public class ListProductItem {

	/*
	 * user_id, product_id, shop_id identify unique rows of table listProductItem on the database
	 * Only for easiness we declared another primary key (id)
	 */
	
	String id = null;
	String userId = null;
	String shopId = null;
	String productId = null;
	boolean completed = false;
	boolean rated = false;
	int realValue = 0;
	
	public ListProductItem(String id, String userId, String shopId, String productId, boolean completed, boolean rated, int realValue) {
		this.id = id;
		this.userId = userId;
		this.shopId = shopId;
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