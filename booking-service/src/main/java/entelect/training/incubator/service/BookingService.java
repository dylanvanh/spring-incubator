package entelect.training.incubator.service;

import entelect.training.incubator.dto.CreateBookingDto;
import entelect.training.incubator.dto.CustomerDto;
import entelect.training.incubator.dto.FlightDto;
import entelect.training.incubator.model.Booking;
import entelect.training.incubator.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private RestTemplate restTemplate;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
    }

    public Booking findById(int id) throws Exception {

        Booking foundEntity = bookingRepository.findById(id).orElse(null);

        if (foundEntity != null) {
            return foundEntity;
        } else {
            throw new Exception("Booking not found");
        }

    }


    public Booking createBooking(CreateBookingDto createBookingDto) throws Exception {
        String flightUrl = "http://localhost:8202/flights/" + createBookingDto.getFlightId();
        String customerUrl = "http://localhost:8201/customers/" + createBookingDto.getCustomerId();

        try {
            FlightDto flight = restTemplate.getForEntity(flightUrl, FlightDto.class).getBody();
            CustomerDto customer = restTemplate.getForEntity(customerUrl, CustomerDto.class).getBody();

            //create booking
            Booking newBooking = new Booking(customer.getId(), flight.getId());
            bookingRepository.save(newBooking);
            return newBooking;
        } catch (Exception e) {
            throw new Exception("Invalid customer or flight id provided");
        }
    }

}
