package com.guanxf.mq.template;

import java.io.IOException;

import org.springframework.amqp.AmqpException;

@SuppressWarnings("serial")
public class SendRefuseException extends Exception {

	public SendRefuseException(String str) {
		System.out.println("Exception:"+str);
	}

	public SendRefuseException(IOException e) {
		e.printStackTrace();
	}

	public SendRefuseException(String str, AmqpException e) {
		System.out.println("Exception:"+str);
				e.printStackTrace();
	}
	
  
}
