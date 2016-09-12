package gr.di.uoa.sl.mvc.service;

import java.util.List;

import gr.di.uoa.sl.mvc.dao.RatingsDao;
import gr.di.uoa.sl.pojos.Ratings;
import gr.di.uoa.sl.pojos.Shop;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingsManager {

	private RatingsDao ratingsDao = null;
	private ShopManager shopManager = null;
	
	
	@Inject
	public void setRatingsDao(RatingsDao ratingsDao) {
		this.ratingsDao = ratingsDao;
	}
	
	@Inject
	public void setShopManager(ShopManager shopManager) {
		this.shopManager = shopManager;
	}

	@Transactional(readOnly=true)
	public Ratings getRatingsByListProductItemId(String listProductItemId) {
		return ratingsDao.getRatingsByListProductItemId(listProductItemId);
	}
	
	@Transactional(readOnly=true)
	public List<Ratings> getRatingsByShop(String shopId) {
		Shop shop = shopManager.getShopById(shopId);
		if (shop==null)
			return null;
		
		return ratingsDao.getRatingsByShop(shopId);
	}
	
	@Transactional(rollbackFor={Exception.class})
	public void createRating(Ratings ratings) {
		ratingsDao.create(ratings);
	}
	
}