package com.hd.app.process;

import com.hd.app.feed.monitor.AppBeanFactory;
import com.hd.app.feed.monitor.FeedListener;

public class HDProcessor {

 /**
   * @param args
   */
  public static void main(String[] args) {


  try {
    AppBeanFactory.loadFramework();

   // enableFeedListner(args);
    FeedListener feedListner = (FeedListener) AppBeanFactory
      .getBean("feedListener");
    feedListner.startListening();

  } catch (Exception e) {
    e.printStackTrace();
    System.exit(1);
   }

 }

}


