package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guanxf.mq.adapter.EventControlConfig;
import com.guanxf.mq.bean.People;
import com.guanxf.mq.controller.DefaultEventController;
import com.guanxf.mq.processer.EventProcesser;
import com.guanxf.mq.template.EventTemplate;
import com.guanxf.mq.template.SendRefuseException;

public class RabbitMqTest {
	private String defaultHost = "127.0.0.1";  
    
    private String defaultExchange = "EXCHANGE_DIRECT_TEST";  
      
    private String defaultQueue = "QUEUE_TEST";  
      
    private DefaultEventController controller;  
      
    private EventTemplate eventTemplate;  
      
    @Before  
    public void init() throws IOException{  
        EventControlConfig config = new EventControlConfig(defaultHost);  
        controller = DefaultEventController.getInstance(config);  
        eventTemplate = controller.getEopEventTemplate();  
        controller.add(defaultQueue, defaultExchange, new ApiProcessEventProcessor());  
        controller.start();  
    }  
      
    @Test  
    public void sendString() throws SendRefuseException{  
        eventTemplate.send(defaultQueue, defaultExchange, "hello world");  
    }  
      
    @Test  
    public void sendObject() throws SendRefuseException{  
        eventTemplate.send(defaultQueue, defaultExchange, mockObj());  
    }  
      
    @Test  
    public void sendTemp() throws SendRefuseException, InterruptedException{  
        String tempExchange = "EXCHANGE_DIRECT_TEST_TEMP";//以前未声明的exchange  
        String tempQueue = "QUEUE_TEST_TEMP";//以前未声明的queue  
        eventTemplate.send(tempQueue, tempExchange, mockObj());  
        //发送成功后此时不会接受到消息，还需要绑定对应的消费程序  
        controller.add(tempQueue, tempExchange, new ApiProcessEventProcessor());  
    }  
      
    @After  
    public void end() throws InterruptedException{  
        Thread.sleep(2000);  
    }  
      
    private People mockObj(){  
        People jack = new People();  
        jack.setId(1);  
        jack.setName("JACK");  
        jack.setMale(true);  
          
        List<People> friends = new ArrayList<>();  
        friends.add(jack);  
        People hanMeiMei = new People();  
        hanMeiMei.setId(1);  
        hanMeiMei.setName("韩梅梅");  
        hanMeiMei.setMale(false);  
        hanMeiMei.setFriends(friends);  
          
        People liLei = new People();  
        liLei.setId(2);  
        liLei.setName("李雷");  
        liLei.setMale(true);  
        liLei.setFriends(friends);  
        liLei.setSpouse(hanMeiMei);  
        hanMeiMei.setSpouse(liLei);  
        return hanMeiMei;  
    }  
      
    class ApiProcessEventProcessor implements EventProcesser{  
        @Override  
        public void process(Object e) {//消费程序这里只是打印信息  
//            Assert.assertNotNull(e);  
            System.out.println(e);  
            if(e instanceof People){  
                People people = (People)e;  
                System.out.println(people.getSpouse());  
                System.out.println(people.getFriends());  
            }  
        }  
    }  
}
