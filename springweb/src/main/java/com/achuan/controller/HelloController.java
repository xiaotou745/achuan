package com.achuan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achuan.redissample.HelloWordService;

@Controller
public class HelloController {
	
	/**
	 * @to 明源
	 * @questions: 这里引用的是redissample项目里随便写的一个类 无论使用@autowired 还是 @resource 注入，都报错。
	 * 用maven install之后，生成的war放到tomcat下没有问题；
	 * 看下哪里搞错了。这个问题整了我好几天了。
	 * 
	 * java.lang.NoClassDefFoundError: Lcom/achuan/redissample/HelloWordService;
			at java.lang.Class.getDeclaredFields0(Native Method)
			at java.lang.Class.privateGetDeclaredFields(Unknown Source)
			at java.lang.Class.getDeclaredFields(Unknown Source)
			at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.findResourceMetadata(CommonAnnotationBeanPostProcessor.java:327)
			at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.postProcessMergedBeanDefinition(CommonAnnotationBeanPostProcessor.java:283)
			at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyMergedBeanDefinitionPostProcessors(AbstractAutowireCapableBeanFactory.java:924)
	 */
	//@Resource
	@Autowired
	private HelloWordService helloWorldService;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public @ResponseBody Object search(HttpServletRequest request, HttpServletResponse response,String name){
		return helloWorldService.sayHello(name);
	}
}
