package test.annotation.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.AccessControlException;
import java.util.Arrays;

public class AccessInvocationHandler<T> implements InvocationHandler {
    final T accessObj;
    public AccessInvocationHandler(T accessObj) {
        this.accessObj = accessObj;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	
    	System.out.println("method name ==== " + method.getName());
    	
    	Method[] methods = accessObj.getClass().getMethods() ; 
    	
    	
//    	method.getName(),method.getParameterTypes()
    	RequiredRoles annotation = accessObj.getClass().getMethod(method.getName(), method.getParameterTypes())
    			                .getAnnotation(RequiredRoles.class);
    	
    	if (annotation != null) {
            System.out.println("annotation  ---> " + Arrays.toString(annotation.value()) );
         }

        if (annotation != null) {
            
        	String[] roles = annotation.value();
//        	String roles = annotation.value();
            //String role = AccessControl.getCurrentRole();
            String role = "admin" ;
            
            System.out.println("has roles is ==== " + Arrays.toString(roles));
            
            if (!Arrays.asList(roles).contains(role)) {
                throw new AccessControlException("The user is not allowed to invoke this method.");
            }else{
            	
                System.out.println(" go >> ");

            }
        }
//        
        
        System.out.println("before calling");  
         Object res =  method.invoke(accessObj, args);
         System.out.println("after calling");  
         return res ; 
    } 
} 