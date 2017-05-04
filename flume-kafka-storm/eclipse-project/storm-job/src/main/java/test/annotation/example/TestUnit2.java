package test.annotation.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestUnit2 {
	
	public static void main(String[] args)  throws Exception {
////		我们要代理的真实对象
		TestRequiredRoles  tr = new TestRequiredRolesImpl() ; 
	
////    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
		InvocationHandler handler = new AccessInvocationHandler<TestRequiredRoles>(tr) ;
//
		TestRequiredRoles subject = (TestRequiredRoles)Proxy.newProxyInstance(handler.getClass().getClassLoader(), tr
	                .getClass().getInterfaces(), handler);
		subject.doSomething() ;
		
	  
		}
		
	}

