package com.hd.app.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface HardDollarAsyncOutService {
	
	@WebMethod
	public void getInputData(String str);
	
	
	
}

