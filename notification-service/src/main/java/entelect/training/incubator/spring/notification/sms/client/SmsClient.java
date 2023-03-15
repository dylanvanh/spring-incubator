package entelect.training.incubator.spring.notification.sms.client;

import entelect.training.incubator.spring.notification.sms.client.impl.BookingQueueDetailsDto;

public interface SmsClient {
    
    void sendSms(BookingQueueDetailsDto bookingDetailsQueueItem);
}
