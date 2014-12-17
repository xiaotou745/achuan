package com.achuan.controller.HelloWorldController;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achuan.redissample.HelloWordService;

@Controller
public class HelloController {
	
	@Resource
	private HelloWordService helloWorldService;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public @ResponseBody Object search(HttpServletRequest request, HttpServletResponse response,String name){
		return helloWorldService.sayHello(name);
	}
}
