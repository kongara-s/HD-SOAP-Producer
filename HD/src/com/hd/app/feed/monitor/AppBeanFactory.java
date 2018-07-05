
package com.hd.app.feed.monitor;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppBeanFactory {
	protected static AbstractApplicationContext context;

	public static ApplicationContext getContext() {
		if (context == null) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("config/spring/applicationContext.xml");

			// let's decrypt the database passwords.
			context = new ClassPathXmlApplicationContext((String[]) list.toArray(new String[0]));
		}
		return context;
	}

	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	/**
	 * Load spring framework
	 */
	public static void loadFramework() {
		getContext();
	}
}
