package td.agent;

/**
 * 
 * @author shawn.wang
 *  一个消息实体
 */
public class Msg {
	
	public String message ;
	public String time ; 
	public String tag ;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		String msg =  "message：" + this.getMessage() +"\r\n"
				+ "time："+this.getTime() +"\r\n"
			     + "tag:"+this.getTag() ;
	              
		return msg;
	}
}
