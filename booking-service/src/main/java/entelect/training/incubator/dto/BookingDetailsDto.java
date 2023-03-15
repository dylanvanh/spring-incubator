package entelect.training.incubator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDetailsDto {
    private String flightNumber;
    private String fullName;
    private String flightDepartureDate;
    private String phoneNumber;
}
