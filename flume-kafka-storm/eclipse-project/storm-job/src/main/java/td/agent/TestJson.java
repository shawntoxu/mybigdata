package td.agent;

import com.google.gson.Gson;

public class TestJson {
	public static void main(String[] args) {

		String logstr = "{\"message\":\"2017-03-02 04:56:27 INFO  SdpIntegrationServiceImpl:252 ¨C 1\",\"time\":\"1489027199\",\"tag\":\"gktkey.gkt.172_30_80_127.853\"}";
        Gson gson = new Gson();
        Msg  cur_msg =  gson.fromJson(logstr, Msg.class) ;
        System.out.println(cur_msg.getTag());
//        String ss = gson.toJson(logstr);
//        System.out.println(ss);
	}
}
