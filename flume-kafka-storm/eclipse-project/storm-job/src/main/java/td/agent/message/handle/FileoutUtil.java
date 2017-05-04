package td.agent.message.handle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import td.agent.Msg;

public class FileoutUtil {
	
   public static final String out  = "/etc/td-agent/config.d/out.txt" ;
	
	public static void printToFile(Object o,Class cls){
		
	      FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(out,true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			//bufferWritter.write("====from plugin "+this.getClass().getName() +"====\r\n" + msg.toString());
			//bufferWritter.write("====from plugin "+this.getClass().getName() +"====\r\n" +Arrays.toString(msgbody) );
			
			if(o instanceof String ){
				bufferWritter.write("====from plugin "+cls.getName() +"====\r\n" +o.toString() );
			}
			else if(o instanceof Arrays){
				bufferWritter.write("====from plugin "+cls.getName() +"====\r\n" +Arrays.toString((Object[])o) );
			}else if(o instanceof Msg){
				bufferWritter.write("====from plugin "+cls.getName() +"====\r\n" +((Msg)o).toString() );
			}else{
				bufferWritter.write("====from plugin "+cls.getName() +"====\r\n" +o.toString() );
			}
			
			bufferWritter.newLine();
			
			// close file writer 
			bufferWritter.flush();
			bufferWritter.close();
			fileWritter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
		
	}

}
