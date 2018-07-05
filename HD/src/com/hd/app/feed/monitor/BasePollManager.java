package com.hd.app.feed.monitor;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sadun.util.polling.CycleEndEvent;
import org.sadun.util.polling.CycleStartEvent;
import org.sadun.util.polling.DirectoryLookupEndEvent;
import org.sadun.util.polling.DirectoryLookupStartEvent;
import org.sadun.util.polling.FileMovedEvent;
import org.sadun.util.polling.FileSetFoundEvent;
import org.sadun.util.polling.PollManager;

public abstract class BasePollManager implements PollManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#cycleEnded(org.sadun.util.polling.
	 * CycleEndEvent)
	 */
	private Log log = LogFactory.getLog(this.getClass().toString());

	public void cycleEnded(CycleEndEvent evt) {
		log.debug("CycleEndEvent");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#cycleStarted(org.sadun.util.polling.
	 * CycleStartEvent)
	 */
	public void cycleStarted(CycleStartEvent evt) {
		log.debug("CycleStartEvent");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#directoryLookupEnded(org.sadun.util.
	 * polling.DirectoryLookupEndEvent)
	 */
	public void directoryLookupEnded(DirectoryLookupEndEvent evt) {
		log.debug("directoryLookupEnded");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sadun.util.polling.PollManager#directoryLookupStarted(org.sadun.util.
	 * polling.DirectoryLookupStartEvent)
	 */
	public void directoryLookupStarted(DirectoryLookupStartEvent evt) {
		log.debug("directoryLookupStarted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sadun.util.polling.PollManager#exceptionDeletingTargetFile(java.io.File)
	 */
	public void exceptionDeletingTargetFile(File target) {
		log.debug("exceptionDeletingTargetFile");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#exceptionMovingFile(java.io.File,
	 * java.io.File)
	 */
	public void exceptionMovingFile(File file, File dest) {
		log.debug("exceptionMovingFile");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#fileMoved(org.sadun.util.polling.
	 * FileMovedEvent)
	 */
	public void fileMoved(FileMovedEvent evt) {
		log.debug("FileMovedEvent");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sadun.util.polling.PollManager#fileSetFound(org.sadun.util.polling.
	 * FileSetFoundEvent)
	 */
	public void fileSetFound(FileSetFoundEvent evt) {
		log.debug("fileSetFound");

	}

}
