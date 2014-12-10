package com.achuan.springsample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.achuan.springsample.autowired.BeanOfAutowired;
import com.achuan.springsample.xml.BeanOfXml;

public class BeanOfAutowiredTests extends TestBase {
	@org.junit.Before
	public void BeforeClass() {
		this.setClassPathXmls("beansOfAutowired.xml");
	}

	@Test
	public void testBean() {
		/**
		 * 获取使用默认构造函数创建的bean
		 */
		BeanOfAutowired bean = ctx.getBean("bean", BeanOfAutowired.class);
		assertNotNull(bean);//获取到
		assertNotNull(bean.getUserBean());
		assertEquals("hello", bean.getMessage());//message 通过setMessage方法注入，成功
		assertEquals("beimai", bean.getBeimai());//beimai 通过字段注入，成功
/*
		BeanOfAutowired beanInjectByConstructor = ctx.getBean("beanInjectByConstructor", BeanOfAutowired.class);
		assertNotNull(beanInjectByConstructor);
		assertNotNull(beanInjectByConstructor.getMessage());
		assertEquals("hello", beanInjectByConstructor.getMessage());
		assertNotNull(beanInjectByConstructor.getUserBean());
		assertEquals(1, beanInjectByConstructor.getUserBean().getUserId());
		assertEquals("beimai", beanInjectByConstructor.getUserBean().getUserName());
		assertEquals("hello", beanInjectByConstructor.getMessage());
		assertEquals("beimai", beanInjectByConstructor.getBeimai());
		
		assertNotNull(beanInjectByConstructor.getUserDao2());
		assertNotNull(beanInjectByConstructor.getUserDao());*/
	}
}
