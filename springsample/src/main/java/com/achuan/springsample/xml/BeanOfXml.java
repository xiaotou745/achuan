package com.achuan.springsample.xml;

import com.achuan.springsample.UserBean;

public class BeanOfXml {
	private String message;

	private UserBean userBean;

	public BeanOfXml() {
	}

	public BeanOfXml(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public BeanOfXml(UserBean userBean,String message){
		this.message = message;
		this.userBean = userBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
