package td.agent.message.handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import td.agent.Msg;

public class LogHandle {
	
	private LogHandle(){}
	
	private static  LogHandle  logHandle  = new LogHandle() ;
	
	public static  LogHandle getInstance(){

		return logHandle ; 
	}
	
	public static Map<String,StreamPlugin> pluginPool = new HashMap<String, StreamPlugin>(); 
	
	//init all plugin 
	public static Map<String,StreamPlugin> initHandlePool() {
		StreamPlugin  logkey_sp =  new Logkey() ;
		pluginPool.put("logkey",logkey_sp);
		return pluginPool;
	}
	
	public  Msg msg  ;
	
	public String jsonMsg ; 
	
	public void  parseMsg(){
		   // send data to tag proxy service 
		String tag = this.msg.getTag() ; 
		String[]  tagArr = tag.split("\\.");
		//get first_tag : eg logkey
		String first_tag =  tagArr.length > 1 ? tagArr[0]:null ;
		
		try {
			StreamPlugin plugin = pluginPool.get(first_tag);
			plugin.doParse(this.msg) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
