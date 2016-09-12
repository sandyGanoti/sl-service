package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.messenger.BuyItemFromShoppingList;
import gr.di.uoa.sl.messenger.ListProductItemMessenger;
import gr.di.uoa.sl.messenger.NewProductToList;
import gr.di.uoa.sl.pojos.ListProductItem;

import java.sql.SQLException;
import java.util.List;

public interface ListProductDao {
	
	public List<ListProductItem> getShoppingListByUser(String userId);
	
	public List<ListProductItem> getShoppingListByShop(String shopId);

	public List<ListProductItem> getShoppingListByProduct(String productId);

	public List<ListProductItem> getShoppingListByUserAndProduct(String userId, String productId);

	public List<ListProductItem> getShoppingListByUserAndShop(String userId, String shopId);

	public ListProductItem getListProductItemByUserAndProductAndShop(String userId, String shopId, String productId);

	public void addProductToShoppingList(NewProductToList newProductToList) throws SQLException;
	
	public void removeProductFromShoppingList(NewProductToList newProductToList) throws SQLException;
	
	public void buyItemFromShoppingList(BuyItemFromShoppingList buyItemFromShoppingList, String listProductItemId) throws SQLException;
	
	public void rateItemFromShoppingList(ListProductItem listProductItem) throws SQLException;
	
}