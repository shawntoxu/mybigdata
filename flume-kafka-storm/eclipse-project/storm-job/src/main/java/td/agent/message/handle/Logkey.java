package td.agent.message.handle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import clojure.main;
import td.agent.Msg;

/**
 *  @author shawn.wang
 *  do handle tag=logkey.xxx.xx.xx
 *  
 */
public class Logkey implements StreamPlugin {
	
	public static Map<String,LogKeyObj>  key_map   =  new HashMap<String,LogKeyObj>();
	
	/**
	 * just write msg  to file 
	 * 
	 * 	/**
	 *  $msgArr:
	 *    'type' => 'ymservice',
		  'app' => 'ym',
		  'come' => 2887667839,
		  'logId' => 9999,
		  'message' => '172.30.80.89 - - \[11/Apr/2017:14:35:22 +0800\] "GET /advertisers/apps?app_id=20&page=1&limit=20\\ HTTP/1.1" 404 - 0',
		  'time' => 1491892532,
	 **/
	public String doParse(Msg msg) throws IOException {

		  String m = msg.getMessage() ;
		  //message split by " "
		  String[] msgbody = m.split(" ");
		  //get mgsbody  : [127.0.0.1, -, -, [13/Apr/2017:10:32:36, +0800], "GET, /advertisers/apps?app_id=20, HTTP/1.1", 404, -, 0]
		  
		  //parse message 
		  String host = msgbody[0] ;
		  String path_with_appid = msgbody[6].indexOf("&")!= -1 ? msgbody[6].split("&")[0]:msgbody[6] ;
		  String status_code =  msgbody[8] ; 
		  String post_size = msgbody[9].equals("-")?"0":msgbody[9] ; 
		  String response_time = msgbody[10] ; 
		  
		  String  keys = host+","+path_with_appid+","+status_code ; 
		  
		  LogKeyObj  logkey ; 
		  if(key_map.containsKey(keys)){
			  logkey = key_map.get(keys) ;
			  logkey.setNum(logkey.getNum()+1);
		  }else{
			  logkey = new LogKeyObj();
			  key_map.put(keys, logkey) ;
		  }
		  logkey.setHost(host);
		  logkey.setPath(path_with_appid);
		  
		  
//		  FileoutUtil.printToFile("host="+host+",path="+path_with_appid+",code="+status_code+",post_size="+post_size+",response_time="+response_time, Logkey.class);
		  FileoutUtil.printToFile(key_map.toString(), Logkey.class);

		  //FileoutUtil.printToFile(msgbody, Logkey.class);

		return null;
	}
   
	
	class LogKeyObj {
		
		
		public LogKeyObj(){
			
			this.num = 1  ;
		}
		private String host ; 
		private String responsetime ; 
		
		
		public String getResponsetime() {
			return responsetime;
		}
		public void setResponsetime(String responsetime) {
			this.responsetime = responsetime;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public int getStatus_code() {
			return status_code;
		}
		public void setStatus_code(int status_code) {
			this.status_code = status_code;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		private String path ; 
		private int status_code  ;
		private int num ;  
		
		
		@Override
		public String toString() {
			String r = "[num="+this.num+" host="+this.host+" path="+this.path+"]" ; 
			return r;
		}
	}
	
	public static void main(String[] args) {
		 Map<String,LogKeyObj>  key_map   =  new HashMap<String,LogKeyObj>();
		 key_map.put("aaa", new Logkey().new LogKeyObj());
		 
		 for(Map.Entry<String, LogKeyObj> entry:key_map.entrySet()){
			 System.out.println(entry.getValue().toString());
		 }
		 
		 System.out.println(key_map.toString()) ;
		 
	}

}
