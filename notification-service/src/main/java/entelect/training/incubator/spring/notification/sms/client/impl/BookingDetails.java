package entelect.training.incubator.spring.notification.sms.client.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDetails {
    private String flightNumber;
    private String fullName;
    private String flightDate;
}
