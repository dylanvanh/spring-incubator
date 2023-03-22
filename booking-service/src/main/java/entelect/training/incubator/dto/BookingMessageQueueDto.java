package entelect.training.incubator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingMessageQueueDto {
    private String flightNumber;
    private String fullName;
    private String flightDepartureDate;
    private String phoneNumber;
}
