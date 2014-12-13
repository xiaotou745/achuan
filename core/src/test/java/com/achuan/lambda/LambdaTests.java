package com.achuan.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class LambdaTests {

	/**
	 * lambda表达式Collectors.toMap测试
	 */
	@Test
	public void testCollectorsToMap() {
		List<User> users = getUsers();

		Map<Integer, String> collect = users.stream().collect(Collectors.toMap(User::getUserId, User::getUserName));
		System.out.println(collect);

		Map<Integer, User> collect2 = users.stream().collect(Collectors.toMap(User::getUserId, u -> u));
		System.out.println(collect2);

		Map<Integer, User> collect3 = users.stream().collect(
				Collectors.toMap(u -> u.getUserId(), Function.<User> identity()));
		System.out.println(collect3);
	}

	private List<User> getUsers() {
		List<User> result = new ArrayList<User>();

		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setUserId(i);
			user.setUserName("username" + i);
			user.setAge(20 + i);
			result.add(user);
		}
		return result;
	}

	class User {
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
}
