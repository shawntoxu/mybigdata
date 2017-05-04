package http.test;

import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.midi.Patch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Request;

// 新版本 httpclient 发送请求
public class NewHttpClientTest {
	//解析数据
	public static void TestGet(String response){
		/**            {
  	  "kind": "NamespaceList",
  	  "apiVersion": "v1",
  	  "metadata": {
  	    "selfLink": "/api/v1/namespaces",
  	    "resourceVersion": "597112"
  	  },
  	  "items": [
  	    {
  	      "metadata": {
  	        "name": "default",
  	        "selfLink": "/api/v1/namespaces/default",
  	        "uid": "bb8174e2-b902-11e5-95a6-028c53cde115",
  	        "resourceVersion": "5",
  	        "creationTimestamp": "2016-01-12T08:01:49Z"
  	      },
  	      "spec": {
  	        "finalizers": [
  	          "kubernetes"
  	        ]
  	      },
  	      "status": {
  	        "phase": "Active"
  	      }
  	    }
  	  ]
  	}
**/
        JSONObject jsonObj = new JSONObject(response);
        JSONArray jsonArray=jsonObj.getJSONArray("items");  
        	for (Object obj : jsonArray) {
        		JSONObject  jobj = (JSONObject)obj ;
//					System.out.println(jobj.toString());
        		System.out.println(jobj.getJSONObject("metadata").get("name"));
			}
		
	}
	
	public static void main(String[] args){
		String apiAddress =
				"http://10.2.33.10:8080/api/v1/namespaces" ;
//				"http://172.30.10.122:8080/api/v1/namespaces/{namespace}/services/{servicename}" ; 
//				"http://172.30.10.122:8080/api/v1/namespaces/kindy/services/gkt-adspush-service" ; 
		
		String testget= "http://m.baidu.com" ;
		String testpost = "http://10.2.9.13:8888/login" ;
		
		CloseableHttpClient client = null ;
		CloseableHttpResponse httpResponse  = null  ;
		
		//config timeout 
		RequestConfig requestConfig = RequestConfig.custom()
		        .setSocketTimeout(1000)
		        .setConnectTimeout(1000)
		        .build();
		
		String response = null ;
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
             client =  httpClientBuilder.build();
             /**
              HttpPatch  httpPatch = new HttpPatch() ;
              StringEntity requestEntity = new StringEntity("") ;
			  httpPatch.setEntity(requestEntity);
			 **/
			 
             // send get 
             HttpGet httpGet = new HttpGet(testget);
             httpResponse =  client.execute(httpGet);
             httpGet.setConfig(requestConfig);
             
//             send post 
             /**
             HttpPost httpPost = new HttpPost(testpost);
              // add paramater
             List <NameValuePair> nvps = new ArrayList <NameValuePair>();
             nvps.add(new BasicNameValuePair("username", "monitor"));
             nvps.add(new BasicNameValuePair("password", "monitor"));
             httpPost.setEntity(new UrlEncodedFormEntity(nvps));
             httpResponse = client.execute(httpPost);
              **/
             HttpEntity entity = httpResponse.getEntity();
              if (entity != null) {  
                  System.out.println("status code :" + httpResponse.getStatusLine());  
                  System.out.println("contentEncoding:" + entity.getContentEncoding());  
//                  System.out.println("response content:" + EntityUtils.toString(entity));  
                   response = EntityUtils.toString(entity) ;
                  EntityUtils.consume(entity);
              }  

            System.out.println("　result is :" +response);
//            TestGet(response);
            
        } catch (Exception e) {
        }finally {
        	try {
				httpResponse.close() ;
				client.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

}

