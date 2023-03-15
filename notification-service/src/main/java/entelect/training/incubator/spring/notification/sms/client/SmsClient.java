package entelect.training.incubator.spring.notification.sms.client;

import entelect.training.incubator.spring.notification.sms.client.impl.BookingDetailsDto;

public interface SmsClient {
    
    void sendSms(String messageDetails);
}
