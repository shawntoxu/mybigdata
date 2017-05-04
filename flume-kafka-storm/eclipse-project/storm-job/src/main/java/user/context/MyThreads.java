package user.context;

public class MyThreads {
	
	public static void main(String[] args) {
		
		
		new Thread(new Runnable() {
			public void run() {
				UserContext  a = UserContext.getInstance();
				a.setName("isme");

				a.PrintInfo();
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				UserContext  a = UserContext.getInstance();
				a.setName("isme-to");

				a.PrintInfo();
			}
		}).start();
		
		
		
	}
	

}
