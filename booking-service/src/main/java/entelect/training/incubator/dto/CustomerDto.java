package entelect.training.incubator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String email;
    private String phoneNumber;

}
