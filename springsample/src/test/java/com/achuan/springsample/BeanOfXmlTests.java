package com.achuan.springsample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.achuan.springsample.autowired.BeanOfAutowired;
import com.achuan.springsample.xml.BeanOfXml;

public class BeanOfXmlTests extends TestBase {
	@org.junit.Before
	public void BeforeClass() {
		this.setClassPathXmls("beansOfXml.xml");
	}

	@Test
	public void testBean() {
		// 默认构造函数bean对象，由于没有注入任何值，所以都为null
		BeanOfXml bean = ctx.getBean("bean", BeanOfXml.class);
		assertNotNull(bean);
		assertNull(bean.getUserBean());
		assertNull(bean.getMessage());

		// 属性注入bean对象，都有值；
		BeanOfXml beanInjectByProperty = ctx.getBean("beanInjectByProperty", BeanOfXml.class);
		assertNotNull(beanInjectByProperty);
		assertNotNull(beanInjectByProperty.getMessage());
		assertEquals("hello", beanInjectByProperty.getMessage());
		assertNotNull(beanInjectByProperty.getUserBean());
		assertEquals(1, beanInjectByProperty.getUserBean().getUserId());
		assertEquals("beimai", beanInjectByProperty.getUserBean().getUserName());

		// 构造函数注入bean
		BeanOfXml beanInjectByConstructor = ctx.getBean("beanInjectByConstructor", BeanOfXml.class);
		assertNotNull(beanInjectByConstructor);
		assertNotNull(beanInjectByConstructor.getMessage());
		assertEquals("hello", beanInjectByConstructor.getMessage());
		assertNotNull(beanInjectByConstructor.getUserBean());
		assertEquals(1, beanInjectByConstructor.getUserBean().getUserId());
		assertEquals("beimai", beanInjectByConstructor.getUserBean().getUserName());
	}
}
