package com.hd.app.soap.webservices;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface="com.hd.app.soap.webservices.HardDollarAsyncOutService")
@HandlerChain(file="config/handler.xml")
public class HardDollarAsyncOutServiceImpl implements HardDollarAsyncOutService {

	@Override
	public void getInputData(String str) {
		System.out.println("The data received from HD system is : "+str);
		
	}

}
