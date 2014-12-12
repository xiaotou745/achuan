package com.achuan.redissample;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTests {
	private static ApplicationContext context = null;
	private static RedisTemplate redisTemplate = null;

	@org.junit.BeforeClass
	public static void BeforeClass() {
		if (context == null) {
			System.out.println("ctx inited....");
			context = new ClassPathXmlApplicationContext("spring-context.xml");
			redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
		}
	}

	@Test
	public void testString() {
		ValueOperations opsForValue = redisTemplate.opsForValue();

		opsForValue.set("string:name", "achuan");
		opsForValue.set("string:id", "1");

		Object name = opsForValue.get("string:name");
		assertNotNull(name);
		assertEquals("achuan", name.toString());
	}

	@Test
	public void testList() {
		ListOperations opsForList = redisTemplate.opsForList();

		String keyName = "ListKey";

		redisTemplate.delete(keyName);

		opsForList.leftPush(keyName, "zhangsan");
		opsForList.leftPush(keyName, "lisi");
		opsForList.leftPushAll(keyName, "wangwu", "zhaoliu", "qianqi");

		Long size = opsForList.size(keyName);
		System.out.println("size:" + size);

		for (long i = 0; i < size; i++) {
			Object value = opsForList.index(keyName, i);
			System.out.println("i:" + i + ",value:" + value.toString());
		}

		Object leftPop = opsForList.leftPop(keyName);
		System.out.println("after pop size:" + opsForList.size(keyName));
	}

	@Test
	public void testObject(){
		List<User> lstUsers = getUsers();
		
		Set keys = redisTemplate.keys("user:*");
		redisTemplate.delete(keys);
		
		ValueOperations opsForValue = redisTemplate.opsForValue();
		
		for (User user : lstUsers) {
			opsForValue.set("user:"+user.getUserId(), user);
		}
		
		List multiGet = opsForValue.multiGet(keys);
		System.out.println(multiGet.size());
	}
	
	private List<User> getUsers(){
		List<User> lstUsers = new ArrayList<User>();
		for (int i=1;i<=10;i++){
			User user = new User();
			user.setAge(20+i);
			user.setUserId(i);
			user.setUserName("name"+i);
			lstUsers.add(user);
		}
		return lstUsers;
	}
}

class User implements Serializable {
	private int userId;
	private String userName;
	private int age;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
