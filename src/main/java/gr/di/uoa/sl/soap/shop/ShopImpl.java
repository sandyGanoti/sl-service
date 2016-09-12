package gr.di.uoa.sl.soap.shop;

import java.util.List;

import gr.di.uoa.sl.mvc.service.ShopManager;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface="gr.di.uoa.sl.soap.shop.Shop")
public class ShopImpl implements Shop {

	ShopManager shopManager;

	@Inject
	public void setShopManager(ShopManager shopManager) {
		this.shopManager = shopManager;
	}


	@Override
	public List<gr.di.uoa.sl.pojos.Shop> getShop() {
		return shopManager.getShops();
	}

}