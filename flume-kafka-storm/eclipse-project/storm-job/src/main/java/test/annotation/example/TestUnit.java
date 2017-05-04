package test.annotation.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestUnit {
	
	public static void main(String[] args)  throws Exception {
////		我们要代理的真实对象
//		TestRequiredRoles  tr = new TestRequiredRolesImpl() ; 
	
////    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
//		InvocationHandler handler = new AccessInvocationHandler<TestRequiredRoles>(tr) ;
//
//		TestRequiredRoles subject = (TestRequiredRoles)Proxy.newProxyInstance(handler.getClass().getClassLoader(), tr
//	                .getClass().getInterfaces(), handler);
//		
//		
//		subject.doSomething() ;
		
		
	              
	            // 通过运行时反射API获得annotation信息  
	            Class<?> rtClass = Class.forName("test.annotation.example.TestRequiredRolesImpl");  
	            Method[] methods = rtClass.getMethods();  
	              
//	            boolean descriptionExist = rtClass.isAnnotationPresent(RequiredRoles.class);  
//	            if (descriptionExist) {  
//	            	RequiredRoles description = (RequiredRoles)rtClass.getAnnotation(RequiredRoles.class);  
//	                System.out.println("Utility's Description --- > " + description.value());  
	                  
	                for (Method method : methods) {  
	                    if (method.isAnnotationPresent(RequiredRoles.class)) {  
	                    	RequiredRoles author = (RequiredRoles)method.getAnnotation(RequiredRoles.class);  
	                        System.out.println("annotation  ---> " + Arrays.toString(author.value()) );  
	                    }  
	                }  
//	            }  
	  
		}
		
	}

