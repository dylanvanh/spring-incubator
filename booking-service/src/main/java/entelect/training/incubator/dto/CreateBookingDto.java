package entelect.training.incubator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingDto {
    private Integer customerId;
    private Integer flightId;

}
