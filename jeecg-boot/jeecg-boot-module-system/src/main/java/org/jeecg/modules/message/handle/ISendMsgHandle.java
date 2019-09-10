package org.jeecg.modules.message.handle;

public interface ISendMsgHandle {

	void sendMsg(String esReceiver, String esTitle, String esContent);
}
