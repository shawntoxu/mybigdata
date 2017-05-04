package td.agent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import td.agent.message.handle.LogHandle;
import td.agent.message.handle.StreamPlugin;

/**
 * 
 * @author shawn.wang
 *  td-agent message handle main class 
 */
public class Test {
	
	public static void out(String filep) throws IOException{
	      //true = append file
	      FileWriter fileWritter = new FileWriter(filep,true);
	             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	             bufferWritter.write("ttttttt");
	             bufferWritter.newLine(); // new line 
	             bufferWritter.flush();
	             bufferWritter.close();
	             fileWritter.close();
	}
	
	//get first tag split by "." 
	public static Map convertToObj(String logstr){
		Map m = new HashMap();
        Gson gson = new Gson();
        Msg  cur_msg =  gson.fromJson(logstr, Msg.class) ;
        String alltag =  cur_msg.getTag();
        //split the tag with . 
        String[] tagArr = alltag.split("\\.");
        //get first tag 
        String first_tag = tagArr.length > 1 ? tagArr[0]:null ;
        
        m.put("msg_obj", cur_msg) ;
        m.put("first_tag",first_tag);
        return m ;
	}
	
	public static void main(String[] args) throws IOException  {
//		   String out = "E:\\share\\storm-job\\src\\main\\java\\td\\agent\\out" ;
//		  String out  = "/etc/td-agent/config.d/out.txt" ;
//	      FileWriter fileWritter = new FileWriter(out,true);
//          BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
          
          
          //args[0]  是一个临时文件，存放发送来的文件内容
          // 文件内容 类似 {"message":"tt","time":"1484707923","tag":"logkey.mytest_log.127.853"}
//          String testin = "E:\\share\\storm-job\\src\\main\\java\\td\\agent\\in" ;
//          FileReader fr = new FileReader(testin);
		
		
		
		LogHandle logHandle = LogHandle.getInstance();
	    logHandle.initHandlePool();
		
		 FileReader fr = new FileReader(args[0]);
		   BufferedReader bf = new BufferedReader(fr);
		   String lineTxt  = "" ;
		   while ((lineTxt = bf.readLine()) != null) {
			   
//			   bufferWritter.write(lineTxt);
			   Map m  = convertToObj(lineTxt);
			   Msg msg_obj = (Msg)m.get("msg_obj");
			   
			   //Object tag = m.get("first_tag");
			   logHandle.msg = msg_obj  ;
			   
//			   StreamPlugin  this_parse  = ClassName.class.newInstance() ; 
//			   bufferWritter.write(Arrays.toString(tagArr));
//             bufferWritter.newLine(); // new line 
			   logHandle.parseMsg();
		   }
		   
//	          bufferWritter.flush();
//	          bufferWritter.close();
//	          fileWritter.close();
		
//		System.out.println(args[0]);
	}

}

/**  
 * Td-agent proxy  处理端
 * 
<match logkey.*.*.*>
    type exec
    command  /usr/lib/jvm/jdk1.8.0_25/bin/java  -classpath /etc/td-agent/config.d/gson-2.3.jar:/etc/td-agent/config.d/commons-lang-2.5.jar:/etc/td-agent/config.d/test.jar td.agent.Test
    format json
    tag_key tag
    time_key time
    buffer_type memory
    buffer_queue_limit 50000
    buffer_chunk_limit 100m
    flush_interval 1s
    retry_wait 5s
    num_threads 5
</match>
**/
