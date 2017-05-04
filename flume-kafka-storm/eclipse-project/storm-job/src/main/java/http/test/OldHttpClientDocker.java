package http.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream.PutField;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.sound.midi.Patch;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
//import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

//采用 老的  org.apache.commons.httpclient   发送网络请求
public class OldHttpClientDocker {
	
	public static String apiAddress =
			"http://172.30.80.127:4243/v1.21/containers/3f8b315147b6/exec" ;
//			"http://172.30.10.122:8080/api/v1/namespaces/{namespace}/services/{servicename}" ; 
//			"http://172.30.10.122:8080/api/v1/namespaces/kindy/services/gkt-adspush-service" ; 
	//POST /exec/(id)/start
	public static String exec_start_api ="http://172.30.80.127:4243/v1.21/exec/%s/start" ;
	
	//POST /exec/(id)/resize
	public static String exec_resize = "http://172.30.80.127:4243/v1.21/exec/%s/resize?h=32&w=157" ; 
   //	GET /exec/(id)/json
	public static String  exec_getjson = "http://172.30.80.127:4243/v1.21/exec/%s/json" ;
	
	
	
	public static void sendGet(String apiAddress,String sendbody,String requestheader){
		 HttpClient client = new HttpClient();
        GetMethod  getMethod = new GetMethod(apiAddress);
//        getMethod.setRequestHeader("Content-Type",requestheader);
        try {
			client.executeMethod(getMethod);
			String response = getMethod.getResponseBodyAsString();
			System.out.println(response);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	public static String sendPost(String apiAddress,String sendbody,String requestheader){
        String response = null ; 

		try {
            HttpClient client = new HttpClient();
            
            /**
            GetMethod  getMethod = new GetMethod(apiAddress);
            client.executeMethod(getMethod);
            String response = getMethod.getResponseBodyAsString();
            **/

            PostMethod postMethod = new PostMethod(apiAddress);
            postMethod.addRequestHeader("Content-Type",requestheader);
            if(null != sendbody){
            StringRequestEntity  ste = new StringRequestEntity(sendbody);
            postMethod.setRequestEntity(ste);
            }
            client.executeMethod(postMethod);
//            NameValuePair[] parameters = new NameValuePair[8];
//            parameters[0] = new NameValuePair("to", to);
//            parameters[1] = new NameValuePair("subject", email.getSubject());
//            postMethod.addParameters(parameters);
//            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            
            if(apiAddress.indexOf("start") != -1){
                InputStream s_response = postMethod.getResponseBodyAsStream() ; 
                 response = IOUtils.toString(s_response, StandardCharsets.UTF_8);	
            }else{
            	 response = postMethod.getResponseBodyAsString();
            }
            
            System.err.println("-----------------------------");
//            System.out.println(response);
            System.err.println("-----------------------------");
		  } catch (Exception e) {
	      
		  }
		
		return response  ;
	}
	
	public static void main(String[] args) {

		try {
            
            String  sendbody = "{\"AttachStdin\": true,"
            		+ "\"AttachStdout\": true,"
            		+ "\"AttachStderr\": true,"
//            		+ "\"Cmd\": [\"/bin/sh\", \"-c\", \"export MyVariable=1234 && echo $MyVariable\"],"
            		+ "\"Cmd\": [\"/bin/bash\"],"
//            		+ "\"Cmd\": [\"/bin/bash\", \"-c\",\"pwd\"],"
            		+ "\"Privileged\": true,"
            		+ "\"Tty\": true,"
            		+ "\"User\": "
            		+ "\"root\"}";
            
            String  response  = sendPost(apiAddress, sendbody, "application/json;charset=UTF-8") ;
            
            //{"Id":"6ee3a564f4408918cb8539f44d720778a1d77f99ba65cc71049cb883aed6df63"}
            //取得分配的bash id  名称列表
            JSONObject jsonObj = new JSONObject(response);
            
            String id= jsonObj.get("Id").toString() ;
            		 
            //System.out.println(id);		 
            
            exec_start_api  =  String.format(exec_start_api, id) ;
            
            String exec_send_body = "{"
									 +"\"Detach\": false,"
									 +"\"Tty\": true,"
									+"}";
            //Upgrade:tcp;Connection: Upgrade;
//           String  exec_result   =  sendPost(exec_start_api, sendbody,"application/json;charset=UTF-8");
//           System.out.println("exec result from console:[ "+exec_result+" ]"); 

            connectToServer(id);
//           
//            String exec_resize_api = String.format(exec_resize,id) ; 
//            
//            sendPost(exec_resize_api, null, "text/plain");
//            
            
            
//            sendGet(String.format(exec_getjson, id), null, "text/plain");
            		 
//            JSONArray jsonArray=jsonObj.getJSONArray("items");  
//            	for (Object obj : jsonArray) {
//            		JSONObject  jobj = (JSONObject)obj ;
////						System.out.println(jobj.toString());
//            		System.out.println(jobj.getJSONObject("metadata").get("name"));
//				}
//            
        } catch (Exception e) {
        }
	}
	
	
	
	public static void connectToServer(String id ){
		 try {  
			 String exec_send_body = "{"
					 +"\"Detach\": false,"
					 +"\"Tty\": true,"
					 +"}";
	   	        //1.建立客户端socket连接，指定服务器位置及端口  
	   	        Socket socket =new Socket("172.30.80.127",4243);  
	   	        //2.得到socket读写流  
	   	        OutputStream os=socket.getOutputStream();  
	   	        PrintWriter pw=new PrintWriter(os);  
	   	        //输入流  
	   	        InputStream is=socket.getInputStream();  
	   	        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
	   	        //3.利用流按照一定的操作，对socket进行读写操作  
	   	        StringBuffer info= new StringBuffer("POST /v1.21/exec/"+id+"/start HTTP/1.1\r\n");
	   	        info.append("Host:172.30.80.127:4243\r\n");
	   	        info.append("Connection:Upgrade\r\n");
	   	        info.append("Content-Type:application/json;charset=utf-8\r\n");
	   	        info.append("Upgrade:tcp\r\n");
	   	        info.append(String.format("Content-Length:%d\r\n",exec_send_body.length())) ;
	   	        info.append(exec_send_body) ;
	   	        info.append("Connection:Keep-Alive\r\n");  
	   	         info.append("Keep-Alive:30\r\n");  
	   	        info.append("\r\n");  
	   	        
	   	        pw.write(info.toString());  
	   	        pw.flush();  
	   	        socket.shutdownOutput();  
	   	        //接收服务器的相应  
	   	        String reply=null;  
	   	        while(!((reply=br.readLine())==null)){  
	   	            System.out.println("from sever ："+reply);  
	   	        }  

	   	        //4.关闭资源  
	   	        br.close();  
	   	        is.close();  
	   	        pw.close();  
	   	        os.close();  
	   	        socket.close();  
	   	    } catch (UnknownHostException e) {  
	   	        e.printStackTrace();  
	   	    } catch (IOException e) {  
	   	        e.printStackTrace();  
	   	    }  
//	           
	}
}  

