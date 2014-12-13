package com.achuan.redissample;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTests {
	private static ApplicationContext context = null;
	private static RedisTemplate<String, Serializable> redisTemplate = null;

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
	public void testObject() {
		List<User> lstUsers = getUsers();

		deleteUsers();

		ValueOperations opsForValue = redisTemplate.opsForValue();

		for (User user : lstUsers) {
			opsForValue.set("user:" + user.getUserId(), user);
		}
		Set keys = redisTemplate.keys("user:*");
		List multiGet = opsForValue.multiGet(keys);
		System.out.println(multiGet.size());
	}

	private void deleteUsers() {
		Set keys = redisTemplate.keys("user:*");
		redisTemplate.delete(keys);
	}

	@Test
	public void testTx() {
		deleteUsers();

		redisTemplate.multi();

		List<User> users = getUsers();

		ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();

		for (User user : users) {
			operations.set("user:" + user.getUserId(), user, 100, TimeUnit.SECONDS);
		}

		redisTemplate.exec();
	}
	@Test
	public void testTx2() {
		deleteUsers();
		final List<User> users = getUsers();
		SessionCallback<User> callback = new SessionCallback<User>() {
			public <K, V> User execute(RedisOperations<K, V> operation) throws DataAccessException {
				operation.multi();
				for(int i=0; i<users.size();i++){
					User user = users.get(i);
					String key = "user:"+user.getUserId();
					BoundValueOperations<String, User> boundValueOps = ((RedisTemplate)operation).boundValueOps(key);
					boundValueOps.set(user, 100, TimeUnit.SECONDS);
				}
				operation.exec();
				return null;
			}
		};
		redisTemplate.execute(callback);
	}

	@Test
	public void testNewsClass() {
		ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();

		Set<String> keys = redisTemplate.keys("class:*");
		redisTemplate.delete(keys);

		List<NewsClass> rootClasses = getNewsClass();
		for (NewsClass newsClass : rootClasses) {
			opsForValue.set(newsClass.getRedisKey(), newsClass);

			List<NewsClass> subClasses = getNewsClassByParentId(newsClass.getClassId());
			Map<String, NewsClass> values = new HashMap<String, NewsClass>();

			for (NewsClass subClass : subClasses) {
				values.put(subClass.getRedisKey(), subClass);
			}
			opsForValue.multiSet(values);
		}

		// parentId=1的keys
		Set<String> keysByParentId = redisTemplate.keys("class:*-1");
		List<Serializable> valuesByParentId = opsForValue.multiGet(keysByParentId);
		for (Serializable serializable : valuesByParentId) {
			NewsClass nc = (NewsClass) serializable;
			System.out.println(nc.toString());
		}
	}

	private List<User> getUsers() {
		List<User> lstUsers = new ArrayList<User>();
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setAge(20 + i);
			user.setUserId(i);
			user.setUserName("name" + i);
			lstUsers.add(user);
		}
		return lstUsers;
	}

	private List<NewsClass> getNewsClass() {
		List<NewsClass> result = new ArrayList<NewsClass>();
		for (int i = 1; i <= 10; i++) {
			NewsClass nc = new NewsClass();
			nc.setClassId(i);
			nc.setClassName("className" + i);
			nc.setParentId(0);
			result.add(nc);
		}
		return result;
	}

	private List<NewsClass> getNewsClassByParentId(int parentId) {
		List<NewsClass> result = new ArrayList<NewsClass>();
		for (int i = 1; i <= 10; i++) {
			NewsClass nc = new NewsClass();
			nc.setClassId(parentId * 10 + i);
			nc.setClassName("className" + parentId * 10);
			nc.setParentId(parentId);
			result.add(nc);
		}
		return result;
	}
}

class NewsClass implements Serializable {
	private int classId;
	private String className;
	private int parentId;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "NewsClass [classId=" + classId + ", className=" + className + ", parentId=" + parentId + "]";
	}

	public String getRedisKey() {
		return "class:" + this.getClassId() + "-" + this.getParentId();
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
