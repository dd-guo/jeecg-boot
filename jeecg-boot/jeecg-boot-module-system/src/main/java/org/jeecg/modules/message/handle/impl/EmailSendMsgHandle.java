package org.jeecg.modules.message.handle.impl;

import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.message.handle.ISendMsgHandle;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSendMsgHandle implements ISendMsgHandle {

	@Override
	public void sendMsg(String esReceiver, String esTitle, String esContent) {
		JavaMailSender mailSender = (JavaMailSender) SpringContextUtils.getBean("mailSender");
		SimpleMailMessage message = new SimpleMailMessage();
		// 设置发送方邮箱地址
		message.setFrom("2897976540@qq.com");
		message.setTo(esReceiver);
		message.setSubject(esTitle);
		message.setText(esContent);
		mailSender.send(message);

	}
}
