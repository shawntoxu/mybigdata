package test.annotation.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestUnit2 {
	
	public static void main(String[] args)  throws Exception {
////		����Ҫ�������ʵ����
		TestRequiredRoles  tr = new TestRequiredRolesImpl() ; 
	
////    ����Ҫ�����ĸ���ʵ���󣬾ͽ��ö��󴫽�ȥ�������ͨ������ʵ�����������䷽����
		InvocationHandler handler = new AccessInvocationHandler<TestRequiredRoles>(tr) ;
//
		TestRequiredRoles subject = (TestRequiredRoles)Proxy.newProxyInstance(handler.getClass().getClassLoader(), tr
	                .getClass().getInterfaces(), handler);
		subject.doSomething() ;
		
	  
		}
		
	}

