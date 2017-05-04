package reflect;

import ch.qos.logback.core.net.SyslogOutputStream;
import clojure.main;

public class Inner {
	
		public Inner() {
			System.out.println("parent cs .");
		}

		private static String  out =  "outer" ;
		
		private  sub  mysub  ;
		
		private  void getOut(){
			new sub() ;
//			return this.out  ;
		}
//		private 
		private static class sub {
			private static String inn = "in" ;
			public sub(){
				System.out.println("sub cs .");
			}
			
		}
		
	public static void main(String[] args) {
		 Inner in = new Inner() ;
		 in.getOut(); 
		 
		 
	}
		
}
