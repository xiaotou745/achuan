package com.achuan.springsample;

import static org.junit.Assert.*;

import org.junit.Test;

import com.achuan.springsample.component.BeanService;
import com.achuan.springsample.component.IBeanService;

public class BeanOfServiceTest extends TestBase {
	
	@org.junit.Before
	public void Before() {
		this.setClassPathXmls("beansOfComponent.xml");
	}

	@Test
	public void test() {
		BeanService bean = ctx.getBean("beanService", BeanService.class);

		assertNotNull(bean);
		
		String userName = bean.getUserName(0);
		
		assertNotNull(userName);
		
		assertEquals("achuan", userName);
		
		IBeanService bean2 = ctx.getBean("beanService",IBeanService.class);
		assertNotNull(bean2);
		
		String userName2 = bean2.getUserName(1);
		
		assertNotNull(userName2);
		assertEquals("achuan", userName2);
	}
}
