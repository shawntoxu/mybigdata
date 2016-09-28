package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class Count {
	
	
	public static void main(String[] args) {
		
		List<String> ll = new ArrayList<String>() ;
			ll.add("key,time,1");
			ll.add("key,time,2");
			ll.add("key,time,2");
			ll.add("key,time,3");
			ll.add("key,time,3");
			ll.add("key,time,4");
			ll.add("key,time,5");

			
		HashMap<String, AtomicInteger>  count = new HashMap<String,AtomicInteger>();
		Set<String> keys = count.keySet();
//		count.put("key,time,1", 1);
		
		for (String string : ll) {
				if(keys.contains(string)){
//					System.out.println(count.get(string).getClass().getSimpleName());
					count.get(string).incrementAndGet() ; 
				}else{
					count.put(string, new AtomicInteger(1));
				}
		}
		
		Iterator<Entry<String,AtomicInteger>> iter = count.entrySet().iterator() ;
		
		while(iter.hasNext()){
		      Entry<String, AtomicInteger> entry = iter.next();
             System.out.println((entry.getKey() + " :\r " + entry.getValue().get()));
		}
		
	}

}
