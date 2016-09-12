package gr.di.uoa.sl.mvc.service;

import gr.di.uoa.sl.mvc.dao.ShopDao;
import gr.di.uoa.sl.pojos.Shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopManager {
	
	private ShopDao shopDao = null;
	
	@Inject
	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}
	
	@Transactional(readOnly=true)
	public List<Shop> getShops() {
		return shopDao.getShops();
	}
	
	@Transactional(readOnly=true)
	public Shop getShopById(String shopId) {
		Shop shop = null;
		if (shopId==null || (shop=shopDao.getShopById(shopId))==null) 
			return null;
		return shop;
	}
	
	public Shop getShopWithMinimumDistance(double userLat, double userLng) {
		List<Shop> shops = getShops();
		Shop shopToReturn = null;
		int minimum = 0;
		for (Shop shop: shops) {
			
			int distance = calculateDistance(userLat, userLng, shop.getLatitude(), shop.getLogtitude());
			
			if (minimum == 0) {
				minimum = distance;
				if (minimum == 0) { 
					shopToReturn = shop;
					break;
				}
			} else {
				if (minimum>distance) {
					minimum = distance;
					shopToReturn = shop;
				}
			}
		}

		return shopToReturn;
	}
	
	
	
	
	public int calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {
		double AVERAGE_RADIUS_OF_EARTH = 6371;
	    
		double latDistance = Math.toRadians(userLat - venueLat);
	    double lngDistance = Math.toRadians(userLng - venueLng);

	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
	      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
	}
}