package com.achuan.cache.redis;

import java.io.InputStream;
import java.util.Properties;

import com.achuan.helper.ParseHelper;

public class RedisConfig {
	private static final Properties prop = new Properties();

	static {
		InputStream inputStream = RedisConfig.class.getClassLoader().getResourceAsStream("redis.properties");
		try {
			prop.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	public static int getMaxTotal(){
		String maxToal = prop.getProperty("redis.max-total");
		return ParseHelper.ToInt(maxToal, 100);
	}

	public static int getMaxIdle() {
		String maxIdle = prop.getProperty("redis.max-idle");
		return ParseHelper.ToInt(maxIdle, 100);
	}

	public static long getWaitMillis() {
		String maxMillis = prop.getProperty("redis.max-millis");
		return ParseHelper.ToLong(maxMillis,2000);
	}

	public static int getTimeout() {
		String timeout = prop.getProperty("redis.timeout");
		return ParseHelper.ToInt(timeout, 2000);
	}
}
