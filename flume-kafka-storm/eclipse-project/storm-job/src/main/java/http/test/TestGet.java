package http.test;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//  okhttp client test
public class TestGet {
	private static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");


    public static String post(String url, String json) throws IOException {
	  RequestBody body = RequestBody.create(JSON, json);
	  Request request = new Request.Builder()
	      .url(url)
	      .post(body)
	      .build();
	  Response response = client.newCall(request).execute();
	  return response.body().string();
	}
		

	public static String run(String url) throws IOException {
	  Request request = new Request.Builder()
	      .url(url)
	      .build();

	  Response response = client.newCall(request).execute();
	  return response.body().string();
	}
	
	
	public static void main(String[] args) throws Exception  {
//		String res = new TestGet().run("http://www.g.cn");
//		System.out.println(res);
		
		String postJosn = "{\"username\":\"monitor\",\"password\":\"monitor\"}" ;
		String res2 = new TestGet().post("http://10.2.9.13:8888/login",postJosn);


	}
}
