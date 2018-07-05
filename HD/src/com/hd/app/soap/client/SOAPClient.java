package com.hd.app.soap.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;	

public class SOAPClient {

	// private static final String serverURI =
	// "http://www.HardDollar.com/webservices/IQueueManager/GetAllInterfaceQueues";
	private static final String serverURI = "http://win-3uunu1osk8s:8081/service/";

	/*
	 * public static void main(String args[]) {
	 * 
	 * new SOAPClient().invokeSOAPAsConsumerProxy(); }
	 */
	public void invokeSOAPAsConsumerProxy(String bodyContent) {
		try {
			/*
			 * change this url pointing to dev
			 * "http://localhost:8080/sws/services/Gateway?wsdl";//
			 * "http://localhost:8080/integration-framework/services/Gateway?wsdl";//http://
			 * vx2223:10001/sws/services/Gateway?wsdl
			 */

			String soapMaterialRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns0=\"http://www.HardDollar.com/webservices\" xmlns:ns1=\"http://schemas.datacontract.org/2004/07/HardDollar.WebServices\">"
					+ "<soapenv:Header/>" + "<soapenv:Body>"
					
					+ bodyContent			 					
					+ "</soapenv:Body>" + "</soapenv:Envelope>";
			

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			InputStream is = new ByteArrayInputStream(soapMaterialRequest.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			//SOAPMessage request = createSOAPRequest(bodyContent);
			/*
			 * SOAPPart part = request.getSOAPPart(); part.detachNode(); SOAPEnvelope env =
			 * part.getEnvelope(); env.detachNode(); SOAPBody body = env.getBody();
			 * body.detachNode(); SOAPHeader head = env.getHeader(); head.detachNode();
			 */
			request.writeTo(System.out);

			MimeHeaders headers = request.getMimeHeaders();
			headers.setHeader("SOAPAction",
					"http://www.HardDollar.com/webservices/ISAPIntegrationService/InsertOrUpdateResources");
			request.saveChanges();
			SOAPMessage soapResponse = soapConnection.call(request, serverURI);
			 //Source response = soapResponse.getSOAPPart().getAllMimeHeaders();
			 //Source response = soapResponse.getSOAPPart().getContent();

			/*
			 * ByteArrayOutputStream out = new ByteArrayOutputStream();
			 * soapResponse.writeTo(out); String reply = out.toString();
			 * System.out.println(reply);
			 */
			//if(StringUtils.isEmpty(soapResponse))
			System.out.println("soapResponse............ "+soapResponse);
			soapConnection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private SOAPMessage createSOAPRequest(String bodyContent) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// envelope.addNamespaceDeclaration("ns1", serverURI);
		envelope.addNamespaceDeclaration("nso", "http://www.HardDollar.com/webservices");
		envelope.addNamespaceDeclaration("ns1", "http://schemas.datacontract.org/2004/07/HardDollar.WebServices");

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("GetAllInterfaceQueues", "nso");
		ByteArrayInputStream is = new ByteArrayInputStream(bodyContent.getBytes());
		
		//soapBodyElem.addTextNode(is);
		// SOAPElement soapBodyElem1 =
		// soapBodyElem.addChildElement("GetAllInterfaceQueues", "web");
		// soapBodyElem1.addTextNode("");
		
		//SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		soapMessage.writeTo(out);
		String strMsg = new String(out.toByteArray());
		System.out.println(strMsg);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		//
		headers.setHeader("SOAPAction", "http://www.HardDollar.com/webservices/IQueueManager/GetAllInterfaceQueues");
		soapMessage.saveChanges();
		System.out.println(soapMessage.getMimeHeaders());

		return soapMessage;
	}
}