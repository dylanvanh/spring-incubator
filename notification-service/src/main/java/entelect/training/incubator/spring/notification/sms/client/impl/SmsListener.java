package entelect.training.incubator.spring.notification.sms.client.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class SmsListener {
    @RabbitListener(queues = "smsQueue")
    public void receiveMessage(String message){
        System.out.println("Received message" + message);
    }

}
