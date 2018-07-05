package com.hd.app.soap.handler;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HDHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		System.out.println("Server : handleMessage()......");

		Boolean isRequest = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// for response message only, true for outbound messages, false for
		// inbound
		if (!isRequest) {

			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPBody soapBody = soapMsg.getSOAPBody();

				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();
				soapMsg.writeTo(System.out);
				StringWriter sw = new StringWriter();

				try {
					TransformerFactory
							.newInstance()
							.newTransformer()
							.transform(new DOMSource(soapMsg.getSOAPBody()),
									new StreamResult(sw));
				} catch (TransformerException e) {
					throw new RuntimeException(e);
				}

				String inputStr = sw.toString();
				if (inputStr.contains("ProjectWBS")) {
					String temp = inputStr.substring(
							inputStr.indexOf("<ProjectWBS"), inputStr.length());
					String xml = temp.substring(0,
							temp.indexOf("</ProjectWBS>"))
							+ "</ProjectWBS>";
					System.out.println(xml);
					StringWriter outStrWriter = new StringWriter();

					outStrWriter.write(xml);
					String dirName = "C:/Users/skongara/transfer/hd/data/CBS/";
					String fileName = "cbs.xml";
					File dir = new File(dirName);
					File actualFile = new File(dir, fileName);

					PrintWriter writer = new PrintWriter(actualFile, "UTF-8");
					writer.println(outStrWriter.toString());
					writer.close();
					
				}else if(inputStr.contains("")){
					
					String temp = inputStr.substring(
							inputStr.indexOf("<ProjectWBS"), inputStr.length());
					String xml = temp.substring(0,
							temp.indexOf("</ProjectWBS>"))
							+ "</ProjectWBS>";
					System.out.println(xml);
					StringWriter outStrWriter = new StringWriter();

					outStrWriter.write(xml);
					String dirName = "C:/Users/skongara/transfer/hd/data/Budget/";
					String fileName = "budget.xml";
					File dir = new File(dirName);
					File actualFile = new File(dir, fileName);

					PrintWriter writer = new PrintWriter(actualFile, "UTF-8");
					writer.println(outStrWriter.toString());
					writer.close();
				}
				// writer.println("The second line");
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
