package com.guanxf.mq.adapter;

import org.springframework.util.ErrorHandler;

public class MessageErrorHandler implements ErrorHandler{  
//    private static final Logger logger = Logger.getLogger(MessageErrorHandler.class);  
    @Override  
    public void handleError(Throwable t) { 
    	System.out.println("0000000000");
//    logger.error("RabbitMQ happen a error:" + t.getMessage(), t);  
    }  
}
