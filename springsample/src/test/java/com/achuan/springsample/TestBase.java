package com.achuan.springsample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
	private String[] classPathXmls;
	protected static ApplicationContext ctx = null;

	protected void setClassPathXmls(String classPathXml) {
		this.classPathXmls = new String[] { classPathXml };
		if (ctx == null) {
			System.out.println("ctx inited....");
			ctx = new ClassPathXmlApplicationContext(this.classPathXmls);
		}
	}
}
