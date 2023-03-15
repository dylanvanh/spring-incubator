package entelect.training.incubator.spring.notification.sms.client.impl;

import com.google.gson.Gson;
import entelect.training.incubator.spring.notification.sms.client.SmsClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * A custom implementation of a fictional SMS service.
 */
@Service
public class MoloCellSmsClient {

    @RabbitListener(queues = "sms_queue")
    public void sendSms(String details) {

        Gson gson = new Gson();
        BookingDetailsDto bookingDetails = gson.fromJson(details, BookingDetailsDto.class);

        System.out.println(bookingDetails);

        String output = String.format("Molo Air: Confirming flight %s booked for %s on %s",
                bookingDetails.getFlightNumber(), bookingDetails.getFullName(), bookingDetails.getFlightDepartureDate());

        System.out.println(String.format("Sending SMS, destination='{}'", bookingDetails.getPhoneNumber()));

        String destinationOutput = String.format("Sending SMS, destination = %s", bookingDetails.getPhoneNumber());

        System.out.println(output);
        System.out.println(destinationOutput);

    }

}
