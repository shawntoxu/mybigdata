package test.annotation.example;

import java.lang.reflect.Proxy;


public class TestRequiredRolesImpl implements TestRequiredRoles {

	@RequiredRoles(value={"user","admin"}) 
	public String doSomething(){
		System.out.println("doSomething .....");
		return null ;
	}

}
