package com.achuan.springsample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.achuan.springsample.resource.BeanOfResource;

public class BeanOfResourceTests extends TestBase {
	@org.junit.Before
	public void Before(){
		this.setClassPathXmls("beansOfResource.xml");
	}
	
	@Test
	public void test(){
		//使用默认构造函数
		BeanOfResource bean = ctx.getBean("bean", BeanOfResource.class);
		assertNotNull(bean);//不为空
		assertEquals("hello", bean.getMessage());//字段注入成功
		assertEquals("beimai", bean.getBeimai());//set方法注入成功
		assertNotNull(bean.getUserBean()); //字段注入成功了，没有加name属性，也会成功；
		assertEquals(1, bean.getUserBean().getUserId());
		assertEquals("beimai", bean.getUserBean().getUserName());
		
		assertNotNull(bean.getUserDao());
	}
}
