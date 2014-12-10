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
		BeanOfXml bean = ctx.getBean("bean", BeanOfXml.class);
		assertNotNull(bean);
		assertNull(bean.getUserBean());
		assertNull(bean.getMessage());

		BeanOfXml beanInjectByProperty = ctx.getBean("beanInjectByProperty", BeanOfXml.class);
		assertNotNull(beanInjectByProperty);
		assertNotNull(beanInjectByProperty.getMessage());
		assertEquals("hello", beanInjectByProperty.getMessage());
		assertNotNull(beanInjectByProperty.getUserBean());
		assertEquals(1, beanInjectByProperty.getUserBean().getUserId());
		assertEquals("beimai", beanInjectByProperty.getUserBean().getUserName());

		BeanOfXml beanInjectByConstructor = ctx.getBean("beanInjectByConstructor", BeanOfXml.class);
		assertNotNull(beanInjectByConstructor);
		assertNotNull(beanInjectByConstructor.getMessage());
		assertEquals("hello", beanInjectByConstructor.getMessage());
		assertNotNull(beanInjectByConstructor.getUserBean());
		assertEquals(1, beanInjectByConstructor.getUserBean().getUserId());
		assertEquals("beimai", beanInjectByConstructor.getUserBean().getUserName());
	}
}
