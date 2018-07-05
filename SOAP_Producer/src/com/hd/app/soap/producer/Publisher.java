package com.hd.app.soap.producer;

import javax.xml.ws.Endpoint;

import com.hd.app.soap.webservices.HardDollarAsyncOutServiceImpl;


public class Publisher {
	
	
	public static void main(String args[]) {
		Endpoint.publish("http://localhost:8080/HardDollar_Async_Out/",
				new HardDollarAsyncOutServiceImpl());

	}
}
