package com.achuan.springsample.resource;

import com.achuan.springsample.UserBean;
import com.achuan.springsample.UserDao;

import javax.annotation.Resource;

/**
 * @see @Resource（JSR-250标准注解，推荐使用它来代替Spring专有的@Autowired注解） Spring
 *      不但支持自己定义的@Autowired注解， 还支持几个由JSR -250规范定义的注解，它们分别是
 *      {@code  @Resource、@PostConstruct以及@PreDestroy。} {@code @Resource}
 *      的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了。
 *      {@code @Resource}
 *      有两个属性是比较重要的，分别是name和type，Spring将@Resource注解的name属性解析为bean的名字，
 *      而type属性则解析为bean的类型。
 *      所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略
 *      。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。 {@code @Resource} 装配顺序
 *      如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
 *      如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
 *      如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常 如果既没有指定name，又没有指定type
 *      ，则自动按照byName方式进行装配（见2）；如果没有匹配，则回退为一个原始类型（UserDao ）进行匹配，如果匹配则自动装配；
 * @author achuan
 *
 */
public class BeanOfResource {
	
	@Resource(name="message")
	private String message;
	private String beimai;
	@Resource
	private UserDao userDao;
	@Resource
	private UserBean userBean;
	
	public BeanOfResource(){
		
	}
	public BeanOfResource(UserBean userBean){
		this.userBean = userBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBeimai() {
		return beimai;
	}

	@Resource
	public void setBeimai(String beimai) {
		this.beimai = beimai;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
