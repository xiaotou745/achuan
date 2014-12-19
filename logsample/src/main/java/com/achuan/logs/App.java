package com.achuan.logs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HelloWorldLog4J log4jSample = new HelloWorldLog4J();
    	log4jSample.runMe("log4j sample");
    	
    	HelloWorldSlf4j slf4jSample = new HelloWorldSlf4j();
    	slf4jSample.runMe("slf4j sample");
    }
}
