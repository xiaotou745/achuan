package com.achuan.springsample.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import com.achuan.springsample.UserBean;
import com.achuan.springsample.UserDao;

/**
 * @Autowired 注解（不推荐使用，建议使用@Resource）
 * @Autowired 可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作。
 *            以上不同实现方式中，@Autowired的标注位置不同，它们都会在Spring在初始化bean时，自动装配被标注的属性，
 *            区别是：标注在成员变量上，Spring会直接将成员类型的唯一一个bean赋值给这个成员变量；
 *            标注在方法上，Spring会调用setXXX方法来将成员类型的唯一一个bean装配到这个属性。
 *            要使@Autowired能够工作，还需要在配置文件中加入以下代码 <bean class=
 *            "org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor"
 *            />
 * @author Administrator
 *
 */
public class BeanOfAutowired {
	private String message;
	private UserBean userBean;
	/**
	 * 字段注入：通过将@Autowired注解放在字段上来完成字段注入。
	 */
	@Autowired
	// 字段注入
	private String beimai;

	/**
	 * {@code}@Autowired 是根据类型进行自动装配的。例如，如果当Spring上下文中存在不止一个UserDao类型的bean时，
	 * 就会抛出BeanCreationException异常
	 * ;如果Spring上下文中不存在UserDao类型的bean，也会抛出BeanCreationException异常
	 * 。我们可以使用@Qualifier配合@Autowired来解决这些问题。 如下： No qualifying bean of type
	 * [com.achuan.springsample.UserDao] is defined: expected single matching
	 * bean but found 2: userDao1,userDao2 {@code @Qualifier：限定描述符，用于细粒度选择候选者；}
	 */
	@Autowired
	@Qualifier("userDao1")
	private UserDao userDao;

	/**
	 * 此属性注入不会报错，貌似根据名称找到了。
	 */
	@Autowired(required = true)
	@Qualifier("userDao2")
	private UserDao userDao2;

	/**
	 * 方法参数注入：通过将@Autowired注解放在方法上来完成方法参数注入。
	 * 
	 * @param message
	 * @see 方法参数注入除了支持setter方法注入，还支持1个或多个参数的普通方法注入，在基于XML配置中
	 *      不支持1个或多个参数的普通方法注入，方法注入不支持静态类型方法的注入。
	 */
	@Autowired
	public void setMessage(String message) {
		this.message = message;
	}

	public BeanOfAutowired() {
	}

	/**
	 * 构造器注入：通过将@Autowired注解放在构造器上来完成构造器注入，默认构造器参数通过类型自动装配，如下所示：
	 * 
	 * @param userBean
	 */
	@Autowired
	public BeanOfAutowired(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getMessage() {
		return message;
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

	public UserDao getUserDao2() {
		return userDao2;
	}

	public void setUserDao2(UserDao userDao2) {
		this.userDao2 = userDao2;
	}
}
