package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.pojos.Shop;

import java.util.List;

public interface ShopDao {
	
	public List<Shop> getShops();
	public Shop getShopById(String shopId);
	public void getShopDetails(String shopId);
	
}