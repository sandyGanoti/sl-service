package gr.di.uoa.sl.mvc.dao;

import java.util.List;

import gr.di.uoa.sl.pojos.Ratings;

public interface RatingsDao {

	public Ratings getRatingsByListProductItemId(String listProductItemId);
	
	public List<Ratings> getRatingsByShop(String shopId);
	
	public void create(Ratings ratings);
	
}