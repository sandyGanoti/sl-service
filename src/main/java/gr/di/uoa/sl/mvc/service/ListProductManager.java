package gr.di.uoa.sl.mvc.service;

import gr.di.uoa.sl.messenger.BuyItemFromShoppingList;
import gr.di.uoa.sl.messenger.ListProductItemMessenger;
import gr.di.uoa.sl.messenger.Messages;
import gr.di.uoa.sl.messenger.NewProductToList;
import gr.di.uoa.sl.messenger.RateItemMessenger;
import gr.di.uoa.sl.mvc.dao.ListProductDao;
import gr.di.uoa.sl.pojos.ListProductItem;
import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.Ratings;
import gr.di.uoa.sl.pojos.Shop;
import gr.di.uoa.sl.pojos.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListProductManager {
	
	private ListProductDao listProductDao = null;
	private UserTableManager userManager = null;
	private ProductManager productManager = null;
	private ShopManager shopManager = null;
	private RatingsManager ratingsManager = null;
	
	@Inject
	public void setListProductEntityDao(ListProductDao listProductEntityDao) {
		this.listProductDao = listProductEntityDao;
	}
	
	@Inject
	public void setUserManager(UserTableManager userManager) {
		this.userManager = userManager;
	}

	@Inject
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	@Inject
	public void setShopManager(ShopManager shopManager) {
		this.shopManager = shopManager;
	}
	
	@Inject
	public void setRatingsManager(RatingsManager ratingsManager) {
		this.ratingsManager = ratingsManager;
	}
	
	@Transactional(readOnly = true)
	public List<ListProductItemMessenger> getShoppingListByUser(String userId) {
		List<ListProductItem> items =  listProductDao.getShoppingListByUser(userId);
		List<ListProductItemMessenger> result = new ArrayList<ListProductItemMessenger>();
		for (ListProductItem lpi: items) {
			Product product = productManager.getProductById(lpi.getProductId());
			String shopName =null;
			if (lpi.getShopId()!=null) {
				Shop shop = shopManager.getShopById(lpi.getShopId());
				if (shop!=null)
					shopName = shop.getName();
			}
			
			ListProductItemMessenger m = new ListProductItemMessenger(lpi.getId(), lpi.getUserId(), shopName, lpi.getShopId(), product.getName(), lpi.getProductId(), lpi.isCompleted(), lpi.isRated(), lpi.getRealValue());
			result.add(m);
		}
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<ListProductItem> getShoppingListByShopId(String shopId) {
		return listProductDao.getShoppingListByShop(shopId);
	}

	@Transactional(readOnly = true)
	public List<ListProductItem> getShoppingListByProductId(String productId) {
		return listProductDao.getShoppingListByProduct(productId);
	}

	@Transactional(readOnly = true)
	public List<ListProductItem> getShoppingListByUserIdAndProductId(String userId, String productId) {
		return listProductDao.getShoppingListByUserAndProduct(userId, productId);
	}

	@Transactional(readOnly = true)
	public List<ListProductItem> getShoppingListByUserIdAndShopId(String userId, String shopId) {
		return listProductDao.getShoppingListByUserAndShop(userId, shopId);
	}

	@Transactional(readOnly = true)
	public ListProductItem getListProductItemByUserIdAndProductIdAndShopId(String userId, String shopId, String productId) {
		return listProductDao.getListProductItemByUserAndProductAndShop(userId, shopId, productId);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void removeProductFromShoppingList(NewProductToList newProductToList) throws Exception {
		if (newProductToList.getProductId()==null || newProductToList.getProductId().isEmpty() ||
			newProductToList.getUserToken()==null || newProductToList.getUserToken().isEmpty())
			throw new Exception("Resources not found");
			
		User user = userManager.authenticateUser(newProductToList.getUserToken());
		if (user==null) throw new Exception(Messages.getNotFoundMessage("User", newProductToList.getUserToken()));
		
		gr.di.uoa.sl.pojos.Product product = productManager.getProductById(newProductToList.getProductId());
		if (product==null) throw new Exception(Messages.getNotFoundMessage("Product", newProductToList.getProductId()));
		
		listProductDao.removeProductFromShoppingList(newProductToList);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void addProductToShoppingList(NewProductToList newProductToList) throws Exception {
		if (newProductToList.getProductId()==null || newProductToList.getProductId().isEmpty() ||
			newProductToList.getUserToken()==null || newProductToList.getUserToken().isEmpty())
			throw new Exception("Resources not found");
			
		User user = userManager.authenticateUser(newProductToList.getUserToken());
		if (user==null) throw new Exception(Messages.getNotFoundMessage("User", newProductToList.getUserToken()));
		
		gr.di.uoa.sl.pojos.Product product = productManager.getProductById(newProductToList.getProductId());
		if (product==null) throw new Exception(Messages.getNotFoundMessage("Product", newProductToList.getProductId()));
		
		listProductDao.addProductToShoppingList(newProductToList);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void buyItemFromShoppingList(BuyItemFromShoppingList itemFromShoppingList) throws Exception {
		if (itemFromShoppingList.getProductId()==null || itemFromShoppingList.getProductId().isEmpty() ||
			itemFromShoppingList.getShopId()==null || itemFromShoppingList.getShopId().isEmpty() ||
			itemFromShoppingList.getUserToken()==null || itemFromShoppingList.getUserToken().isEmpty() ||
			itemFromShoppingList.getValue()<0)
			throw new Exception("Resources not found");
			
		gr.di.uoa.sl.pojos.Product product = productManager.getProductById(itemFromShoppingList.getProductId());
		if (product==null) throw new Exception(Messages.getNotFoundMessage("Product", itemFromShoppingList.getProductId()));
		
		Shop shop = shopManager.getShopById(itemFromShoppingList.getShopId());
		if (shop==null) throw new Exception(Messages.getNotFoundMessage("Shop", itemFromShoppingList.getShopId()));
		
		User user = userManager.authenticateUser(itemFromShoppingList.getUserToken());
		if (user==null) throw new Exception(Messages.getNotFoundMessage("Shop", itemFromShoppingList.getUserToken()));
		
		ListProductItem listProductItem = listProductDao.getListProductItemByUserAndProductAndShop(
				itemFromShoppingList.getUserToken(), itemFromShoppingList.getShopId(), itemFromShoppingList.getProductId());
		if (listProductItem==null) throw new Exception("Product was not found in Shopping List");

		listProductDao.buyItemFromShoppingList(itemFromShoppingList, listProductItem.getId());
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void rateItemFromShoppingList(RateItemMessenger rateItemMessenger) throws Exception {
		if (rateItemMessenger.getProductId()==null || rateItemMessenger.getProductId().isEmpty() ||
			rateItemMessenger.getShopId()==null || rateItemMessenger.getShopId().isEmpty() ||
			rateItemMessenger.getUserToken()==null || rateItemMessenger.getUserToken().isEmpty())
			throw new Exception("Resources not found");
	
		ListProductItem listProductItem = listProductDao.getListProductItemByUserAndProductAndShop(
				rateItemMessenger.getUserToken(), rateItemMessenger.getShopId(), rateItemMessenger.getProductId());
		if (listProductItem==null) throw new Exception("Product was not found in Shopping List");

		/** inside on a transaction method: Two actions: update listProductItem table in order to declare
		 *  that the item has rate from now on.. **/
		listProductDao.rateItemFromShoppingList(listProductItem);
		
		/** ..and create a rate on table ratings for that item **/
		/** if one action fails, both fail! **/
		Ratings ratings = new Ratings(listProductItem.getId(), listProductItem.getRealValue(), rateItemMessenger.getComment());
		ratingsManager.createRating(ratings);
	}
	
}