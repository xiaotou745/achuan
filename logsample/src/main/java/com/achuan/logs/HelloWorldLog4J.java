package com.achuan.logs;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HelloWorldLog4J {
	final static Logger logger = LogManager.getLogger(HelloWorldLog4J.class);

	public void runMe(String parameter) {

		if (logger.isDebugEnabled()) {
			logger.debug("This is debug : " + parameter);
		}

		if (logger.isInfoEnabled()) {
			logger.info("This is info : " + parameter);
		}

		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}

}
