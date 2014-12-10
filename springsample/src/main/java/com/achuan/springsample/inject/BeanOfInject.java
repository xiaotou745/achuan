package com.achuan.springsample.inject;

import com.achuan.springsample.UserBean;
import com.achuan.springsample.UserDao;

public class BeanOfInject {
	private String message;
	private String beimai;
	private UserBean userBean;
	private UserDao userDao;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBeimai() {
		return beimai;
	}

	public void setBeimai(String beimai) {
		this.beimai = beimai;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
