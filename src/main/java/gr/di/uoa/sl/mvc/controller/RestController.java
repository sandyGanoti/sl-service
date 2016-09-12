package gr.di.uoa.sl.mvc.controller;

import gr.di.uoa.sl.messenger.AppResponse;
import gr.di.uoa.sl.messenger.AppResponse.Status;
import gr.di.uoa.sl.messenger.BuyItemFromShoppingList;
import gr.di.uoa.sl.messenger.ListProductItemMessenger;
import gr.di.uoa.sl.messenger.Messages;
import gr.di.uoa.sl.messenger.NewProductToList;
import gr.di.uoa.sl.messenger.RateItemMessenger;
import gr.di.uoa.sl.messenger.UserMessenger;
import gr.di.uoa.sl.messenger.UserTokenMessenger;
import gr.di.uoa.sl.mvc.service.ListProductManager;
import gr.di.uoa.sl.mvc.service.ProductManager;
import gr.di.uoa.sl.mvc.service.RatingsManager;
import gr.di.uoa.sl.mvc.service.ShopManager;
import gr.di.uoa.sl.mvc.service.UserTableManager;
import gr.di.uoa.sl.pojos.ListProductItem;
import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.ProductShop;
import gr.di.uoa.sl.pojos.Ratings;
import gr.di.uoa.sl.pojos.Shop;
import gr.di.uoa.sl.pojos.User;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*   http://localhost:8100/sl-service/slservice/getAllProducts   */

@Controller
public class RestController {
	
	private ProductManager productManager;
	private ShopManager shopManager;
	private ListProductManager listProductManager;
	private RatingsManager ratingsManager;
	private UserTableManager userManager; 
	
	@Inject
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	@Inject
	public void setShopManager(ShopManager shopManager) {
		this.shopManager = shopManager;
	}
	
	@Inject
	public void setListProductManager(ListProductManager listProductManager) {
		this.listProductManager = listProductManager;
	}
	
	@Inject
	public void setRatingsManager(RatingsManager ratingsManager) {
		this.ratingsManager = ratingsManager;
	}
	
	@Inject
	public void setUserManager(UserTableManager userManager) {
		this.userManager = userManager;
	}

	@RequestMapping(value="/j/k", method = RequestMethod.GET)
	public @ResponseBody String getAllLogins(HttpServletRequest request) throws Exception {
		return "justTest";
	}
	
	@RequestMapping(value = "/slservice/login", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody AppResponse login(@RequestBody UserMessenger user, HttpServletRequest request) throws Exception {
		User userStored = null;
		boolean justCreated = false;
		if (user!=null && user.getUsername()!=null &&
				   !user.getUsername().isEmpty() && 
				   user.getPassword()!=null && !user.getPassword().isEmpty())
			userStored = userManager.authenticateUser(user.getUsername(), user.getPassword());
		if (userStored == null) {
			userStored = userManager.signUpUser(user);
		}
		return new AppResponse(Status.Success, userStored, (justCreated? "JustCreated": "Exists"));
	}
	
	
	
	@RequestMapping(value = "/slservice/getShoppingList", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody  AppResponse getShoppingList(@RequestBody UserTokenMessenger mess, HttpServletRequest request)
			throws Exception {
		if (mess==null || mess.getUserId()==null || mess.getUserId().isEmpty())
			return new AppResponse(Status.NotFound, null, "Not such a user!");
		List<ListProductItemMessenger> result = listProductManager.getShoppingListByUser(mess.getUserId());
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Shopping List"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}
		
	@RequestMapping(value = "/slservice/getAllProducts", method = RequestMethod.GET)
	public @ResponseBody  AppResponse getAllProducts(HttpServletRequest request)
			throws Exception {
		List<Product> result = productManager.getProducts();
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Products"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}
	
	@RequestMapping(value = "/slservice/getAllShops", method = RequestMethod.GET)
	public @ResponseBody AppResponse getAllShops(HttpServletRequest request)
			throws Exception {
		List<Shop> result = shopManager.getShops();
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Shops"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}
	
	@RequestMapping(value = "/slservice/getShopDetails", method = RequestMethod.GET)
	public @ResponseBody  AppResponse getShopDetails(@RequestBody String shopId, HttpServletRequest request)
			throws Exception {
		List<Ratings> result = ratingsManager.getRatingsByShop(shopId);
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Details"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}

	@RequestMapping(value = "/slservice/removeProductFromShoppingList", method=RequestMethod.POST, consumes="application/json")
	public AppResponse removeProductFromShoppingList(@RequestBody NewProductToList newProductToList) {
		try {
			listProductManager.removeProductFromShoppingList(newProductToList);
		} catch (SQLException sqle) {
			return new AppResponse(Status.Failure, null, Messages.getNotInsertedMessage("Product", newProductToList.getProductId()));
		} catch (Exception e) {
			return new AppResponse(Status.NotFound, null, e.getMessage());
		}
		return new AppResponse(Status.Success, null, Messages.getInsertedMessage("Product", newProductToList.getProductId()));
	}
	
	@RequestMapping(value = "/slservice/addProductToShoppingList", method=RequestMethod.POST, consumes="application/json")
	public AppResponse addProductToShoppingList(@RequestBody NewProductToList newProductToList) {
		try {
			listProductManager.addProductToShoppingList(newProductToList);
		} catch (SQLException sqle) {
			return new AppResponse(Status.Failure, null, Messages.getNotInsertedMessage("Product", newProductToList.getProductId()));
		} catch (Exception e) {
			return new AppResponse(Status.NotFound, null, e.getMessage());
		}
		return new AppResponse(Status.Success, null, Messages.getInsertedMessage("Product", newProductToList.getProductId()));
	}

	@RequestMapping(value = "/slservice/buyItemFromShoppingList", method=RequestMethod.POST, consumes="application/json")
	public AppResponse buyItemFromShoppingList(@RequestBody BuyItemFromShoppingList itemFromShoppingList) {
		try {
			listProductManager.buyItemFromShoppingList(itemFromShoppingList);
		} catch (SQLException sqle) {
			return new AppResponse(Status.Failure, null, Messages.getNotInsertedMessage("Product", itemFromShoppingList.getProductId()));
		} catch (Exception e) {
			return new AppResponse(Status.NotFound, null, e.getMessage());
		}
		return new AppResponse(Status.Success, null, Messages.getInsertedMessage("Product", itemFromShoppingList.getProductId()));
	}
	
	@RequestMapping(value = "/slservice/rateItemFromShoppingList", method=RequestMethod.POST, consumes="application/json")
	public AppResponse rateItemFromShoppingList(@RequestBody RateItemMessenger rateItemMessenger) {
		try {
			listProductManager.rateItemFromShoppingList(rateItemMessenger);
		} catch (Exception e) {
			return new AppResponse(Status.NotFound, null, e.getMessage());
		}
		return new AppResponse(Status.Success, null, Messages.getInsertedMessage("Product", rateItemMessenger.getProductId()));
	}
	
	@RequestMapping(value = "/slservice/getProductsByShop", method = RequestMethod.POST)
	public @ResponseBody  AppResponse getProductsByShop(@RequestBody String shopId, HttpServletRequest request)
			throws Exception {
		List<Product> result = productManager.getProductsByShop(shopId);
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Products"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}
	
	@RequestMapping(value = "/slservice/getShopsAndValueByProduct", method = RequestMethod.POST)
	public @ResponseBody  AppResponse getShopsAndValueByProduct(@RequestBody String productId, HttpServletRequest request)
			throws Exception {
		List<ProductShop> result = productManager.getShopsAndValueByProduct(productId);
		if (result.isEmpty())
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Details"));
		return new AppResponse(Status.Success, result, Messages.getFoundMessage());
	}
	
	@RequestMapping(value = "/slservice/getShopByProductWithMinimumValue", method = RequestMethod.POST)
	public @ResponseBody  AppResponse getShopByProductWithMinimumValue(@RequestBody String productId, HttpServletRequest request)
			throws Exception {

		ProductShop pse = productManager.getShopByProductWithMinimumValue(productId);
		if (pse==null)
			return new AppResponse(Status.NotFound, null, Messages.getNotFoundMessage("Such shop"));
		return new AppResponse(Status.Success, pse, Messages.getFoundMessage());
	}
	
}