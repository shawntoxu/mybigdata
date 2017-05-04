package test.annotation.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestUnit {
	
	public static void main(String[] args)  throws Exception {
////		����Ҫ�������ʵ����
//		TestRequiredRoles  tr = new TestRequiredRolesImpl() ; 
	
////    ����Ҫ�����ĸ���ʵ���󣬾ͽ��ö��󴫽�ȥ�������ͨ������ʵ�����������䷽����
//		InvocationHandler handler = new AccessInvocationHandler<TestRequiredRoles>(tr) ;
//
//		TestRequiredRoles subject = (TestRequiredRoles)Proxy.newProxyInstance(handler.getClass().getClassLoader(), tr
//	                .getClass().getInterfaces(), handler);
//		
//		
//		subject.doSomething() ;
		
		
	              
	            // ͨ������ʱ����API���annotation��Ϣ  
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

