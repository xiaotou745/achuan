package com.achuan.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldSlf4j {
	final static Logger logger = LoggerFactory.getLogger(HelloWorldSlf4j.class);

	public void runMe(String parameter) {

		if (logger.isDebugEnabled()) {
			logger.debug("This is debug : " + parameter);
		}

		if (logger.isInfoEnabled()) {
			logger.info("This is info : " + parameter);
		}

		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
	}
}
