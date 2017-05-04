package user.context;
	class  UserContext {
		
	 private static ThreadLocal<UserContext>   hold  = new ThreadLocal<UserContext>() ;
	 
	 
	 private String name ;
	 
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public   void  PrintInfo(){
		 System.out.println(Thread.currentThread().getName() + " ---- " + this.getName() );
	 }
	 
	 public static UserContext getInstance(){
		 UserContext  uc = hold.get() ;
		 if(null == uc ){
			 uc = new UserContext();
			 hold.set(uc);
		 }
		 return  uc  ;
	 }
	 
	 public static void clear() {
		 hold.remove();
	 }
		
	}
