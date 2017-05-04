package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.twitter.chill.Base64.InputStream;

public class My {
	
	private String mycode = "01" ;
	private int isnew = 1 ;
	private static final int go = 0 ; 
	
	
	public String getMycode(){
		
		return this.mycode  ;
	}
	
	public static void main(String[] args) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, ParseException, InstantiationException  {
		
		My instance  = My.class.newInstance() ; 
		System.out.println(instance.getMycode());
		
	/**	
		
		String  cname  =  My.class.getSimpleName()  ; 
//		InputStream   in  = ClassLoader.getResourceAsStream(My.class.getName());
		ClassLoader classLoader = My.class.getClassLoader();
		 Field[] fields = My.class.getDeclaredFields();
		
		 for (Field f : fields) {
			 //ÐÞÊÎ·û²»ÊÇfinal 
			 if(!Modifier.isFinal(f.getModifiers())){
				 System.out.println("Type="+f.getType()+": name="+f.getName());
				 
				 Type type = f.getType();
				 String value = "5" ; 
				 
				 //Set value 
				 if ((Integer.TYPE == type) || (Integer.class == type)) {
		                f.setInt(null, Integer.parseInt(value));
		              } else if ((Long.TYPE == type) || (Long.class == type)) {
		                f.setLong(null, Long.parseLong(value));
		              } else if ((Boolean.TYPE == type) || (Boolean.class == type)) {
		                f.setBoolean(null, Boolean.parseBoolean(value));
		              } else if (String.class == type) {
		                f.set(null, value);
		              } else if ((Short.TYPE == type) || (Short.class == type)) {
		                f.setShort(null, Short.parseShort(value));
		              } else if ((Float.TYPE == type) || (Float.class == type)) {
		                f.setFloat(null, Float.parseFloat(value));
		              } else if ((Double.TYPE == type) || (Double.class == type)) {
		                f.setDouble(null, Double.parseDouble(value));
		              } else if (Date.class == type) {
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		                f.set(null, dateFormat.parse(value));
		              }
			 };
			 
			
		} 
		**/
		 
//		System.out.println(cname);
	}

}
