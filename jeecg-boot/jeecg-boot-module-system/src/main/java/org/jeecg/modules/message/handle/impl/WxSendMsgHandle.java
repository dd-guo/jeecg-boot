package org.jeecg.modules.message.handle.impl;

import org.jeecg.modules.message.handle.ISendMsgHandle;

public class WxSendMsgHandle implements ISendMsgHandle {

	@Override
	public void sendMsg(String esReceiver, String esTitle, String esContent) {
		// TODO Auto-generated method stub
		System.out.println("发微信消息模板");
	}

}
