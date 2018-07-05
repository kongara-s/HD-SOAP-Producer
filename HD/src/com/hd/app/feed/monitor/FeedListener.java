package com.hd.app.feed.monitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sadun.util.polling.DirectoryPoller;
import org.sadun.util.polling.FileFoundEvent;

import com.hd.app.soap.client.SOAPClient;

//import com.uprr.app.adx.feed.ADXFileProcessor.AdxMsg;

public class FeedListener {

	private Log log = LogFactory.getLog(this.getClass().toString());
	// private LinkedBlockingQueue<AdxMsg> queue=null;
	private List<Object> directories;
	private SOAPClient soapClient;

	// private ADXFileProcessor adxFileProcessor;

	public SOAPClient getSoapClient() {
		return soapClient;
	}

	public void setSoapClient(SOAPClient soapClient) {
		this.soapClient = soapClient;
	}

	/**
	 * @return
	 */
	public List<Object> getDirectories() {
		return directories;
	}

	/**
	 * @param directories
	 */
	public void setDirectories(List<Object> directories) {
		this.directories = directories;
	}

	/**
	 * startListening.
	 */
	public void startListening() {
		// queue=new LinkedBlockingQueue<AdxMsg>(400);
		for (Iterator<Object> iterator = directories.iterator(); iterator.hasNext();) {
			String directory = (String) iterator.next();
			new FileListenerThread(directory).start();
		}
	}

	/**
	 * Filelistener thread resquired to span multiple threads one for each inbound
	 * directory
	 * 
	 */
	class FileListenerThread extends Thread {
		private String directory;

		// private ADXFileProcessor adxFileProcessor;
		// private LinkedBlockingQueue<AdxMsg> queue;

		/**
		 * @param directory2
		 * @param adxFileProcessor
		 */
		public FileListenerThread(String directory) {
			this.directory = directory;
			// this.adxFileProcessor = adxFileProcessor;
			// this.queue=queue;
		}

		public void run() {

			DirectoryPoller poller = new DirectoryPoller(new File(directory));

			BasePollManager filePoller = new BasePollManager() {
				public void fileFound(FileFoundEvent evt) {
					try {
						File file = evt.getFile();
						// File fXmlFile = new File(file);
						// System.out.println(file.toString());

						String inpuFile = directory + "/archive/"
								+ file.getName().substring(0, file.getName().length() - 4)
								+ DateFormatUtils.format(System.currentTimeMillis(), "MMddyyyyhhmmss") + ".xml";
						file.renameTo(new File(inpuFile));
						log.info("Received file from " + inpuFile);
						BufferedReader br = new BufferedReader(new FileReader(new File(inpuFile)));
						String line;
						StringBuilder sb = new StringBuilder();
						while((line=br.readLine())!= null){
							if(!line.contains("<?xml"))
								sb.append(line.trim());
						}
						if(StringUtils.isNotEmpty(sb)){
							soapClient.invokeSOAPAsConsumerProxy(sb.toString());
						}
							System.out.println(sb.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			poller.addPollManager(filePoller);
			poller.setSendSingleFileEvent(true);
			poller.setAutoMoveDirectory(new File(directory), new File(directory + "/archive"));
			poller.setAutoMove(true);
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if ("xml".equalsIgnoreCase(StringUtils.substringAfterLast(name, "."))) {
						return true;
					}
					return false;
				}
			};

			poller.setFilter(filter);
			poller.start();

		}
	}
}
