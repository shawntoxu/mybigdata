package http.test;

import java.io.ObjectOutputStream.PutField;
import java.util.Date;

import javax.sound.midi.Patch;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
//import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

//采用 老的  org.apache.commons.httpclient   发送网络请求
public class OldHttpClientTest {
	
	public static void main(String[] args) {
		String apiAddress =
				"http://10.2.33.10:8080/api/v1/namespaces" ;
//				"http://172.30.10.122:8080/api/v1/namespaces/{namespace}/services/{servicename}" ; 
//				"http://172.30.10.122:8080/api/v1/namespaces/kindy/services/gkt-adspush-service" ; 
		try {
            HttpClient client = new HttpClient();
            
            //put  改变service 配置文件
//            String putJosn ="{\"kind\":\"Service\",\"apiVersion\":\"v1\",\"metadata\":{\"name\":\"gkt-adspush-service\",\"namespace\":\"kindy\",\"selfLink\":\"/api/v1/namespaces/kindy/services/gkt-adspush-service\",\"uid\":\"551fe889-e98b-11e5-8ccd-0050568104ec\",\"resourceVersion\":\"30146465\",\"creationTimestamp\":\"2016-03-14T02:20:34Z\",\"labels\":{\"name\":\"gkt-adspush-service\"}},\"spec\":{\"ports\":[{\"protocol\":\"TCP\",\"port\":41003,\"targetPort\":8080,\"nodePort\":0}],\"selector\":{\"name\":\"gkt-adspush-web\"},\"clusterIP\":\"10.55.0.191\",\"type\":\"ClusterIP\",\"deprecatedPublicIPs\":[\"172.30.10.191,172.30.10.192,172.30.10.193\"],\"sessionAffinity\":\"None\"},\"status\":{\"loadBalancer\":{}}}";
//            PutMethod  putMethod = new PutMethod(apiAddress);
//            putMethod.addRequestHeader("Content-Type","application/json;charset=UTF-8");
//            putMethod.addRequestHeader("Accept","application/json");
//            StringRequestEntity ste = new StringRequestEntity(putJosn);
//			putMethod.setRequestEntity(ste);
//			client.executeMethod(putMethod);
//			String response = putMethod.getResponseBodyAsString();
//			
			
            GetMethod  getMethod = new GetMethod(apiAddress);
            client.executeMethod(getMethod);
            String response = getMethod.getResponseBodyAsString();

//            PostMethod postMethod = new PostMethod(apiAddress);
//            NameValuePair[] parameters = new NameValuePair[8];
//            parameters[0] = new NameValuePair("to", to);
//            parameters[1] = new NameValuePair("subject", email.getSubject());
//            postMethod.addParameters(parameters);
//            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
//            String response = postMethod.getResponseBodyAsString();
            
            
            System.out.println(response);
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
            //取得namespace 名称列表
            JSONObject jsonObj = new JSONObject(response);
            JSONArray jsonArray=jsonObj.getJSONArray("items");  
            	for (Object obj : jsonArray) {
            		JSONObject  jobj = (JSONObject)obj ;
//						System.out.println(jobj.toString());
            		System.out.println(jobj.getJSONObject("metadata").get("name"));
				}
	
            
        } catch (Exception e) {
        }
	}

}

/**
 {"kind":"Service","apiVersion":"v1","metadata":{"name":"gkt-adspush-service","namespace":"kindy","selfLink":"/api/v1/namespaces/kindy/services/gkt-adspush-service","uid":"551fe889-e98b-11e5-8ccd-0050568104ec","resourceVersion":"30146465","creationTimestamp":"2016-03-14T02:20:34Z","labels":{"name":"gkt-adspush-service"}},"spec":{"ports":[{"protocol":"TCP","port":41003,"targetPort":8080,"nodePort":0}],"selector":{"name":"gkt-adspush-web"},"clusterIP":"10.55.0.191","type":"ClusterIP","deprecatedPublicIPs":["172.30.10.191,172.30.10.192"],"sessionAffinity":"None"},"status":{"loadBalancer":{}}}

 */
