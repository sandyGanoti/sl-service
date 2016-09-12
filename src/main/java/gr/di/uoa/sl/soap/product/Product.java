package gr.di.uoa.sl.soap.product;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface Product {

	@WebMethod List<gr.di.uoa.sl.pojos.Product> getProduct();
	
}