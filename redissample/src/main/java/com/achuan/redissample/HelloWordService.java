package com.achuan.redissample;

import org.springframework.stereotype.Service;

@Service
public class HelloWordService {
	public String sayHello(String name){
		return "hello:" + name;
	}
}
