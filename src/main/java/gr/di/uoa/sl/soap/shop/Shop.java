package gr.di.uoa.sl.soap.shop;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/*service end point interface*/

@WebService
@SOAPBinding(style = Style.RPC)
public interface Shop {

	@WebMethod List<gr.di.uoa.sl.pojos.Shop> getShop();
	
}