package td.agent.message.handle;

import java.io.IOException;
import java.util.List;

import td.agent.Msg;

/**
 * 
 * @author shawn.wang
 * 
 *  stream message handle interface 
 */
public interface StreamPlugin {
	
	public String doParse(Msg msg) throws IOException;

}
