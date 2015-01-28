package com.guanxf.mq.processer;
/** 
 * MessageListenerAdapter的Pojo 
 * <p>消息处理适配器，主要功能：</p> 
 * <p>1、将不同的消息类型绑定到对应的处理器并本地缓存，如将queue01+exchange01的消息统一交由A处理器来出来</p> 
 * <p>2、执行消息的消费分发，调用相应的处理器来消费属于它的消息</p> 
 */  
public class MessageAdapterHandler {

}
