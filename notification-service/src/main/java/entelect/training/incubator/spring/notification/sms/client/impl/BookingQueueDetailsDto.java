package entelect.training.incubator.spring.notification.sms.client.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingQueueDetailsDto {
    private BookingDetails bookingDetails;
    private String status;
    private String message;

}
