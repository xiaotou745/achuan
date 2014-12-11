package com.achuan.springsample.component;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class BeanDao {

	@Resource
	private String userName;

	public String getUserName(int userId) {
		return userName;
	}
}
