package entelect.training.incubator.spring.notification.sms.client.impl;

import entelect.training.incubator.spring.notification.sms.client.SmsClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * A custom implementation of a fictional SMS service.
 */
@Service
public class MoloCellSmsClient implements SmsClient {
    
    @Override
    @RabbitListener(queues = "sms_queue")
    public void sendSms(BookingQueueDetailsDto bookingDetailsQueueItem) {
        System.out.println(String.format("Sending SMS, destination='{}', '{}'"));
    }
}
