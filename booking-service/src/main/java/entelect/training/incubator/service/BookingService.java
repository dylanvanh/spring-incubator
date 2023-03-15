package entelect.training.incubator.service;

import entelect.training.incubator.dto.*;
import entelect.training.incubator.model.Booking;
import entelect.training.incubator.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.awt.print.Book;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private RestTemplate restTemplate;
    private RabbitTemplate rabbitTemplate;

    public static final String QUEUE = "sms_queue";
    public static final String EXCHANGE = "sms_exchange";
    public static final String ROUTING_KEY = "sms_routingKey";

    @Autowired
    public BookingService(BookingRepository bookingRepository, RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
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

            //add to queue
            BookingDetails bookingDetails = new BookingDetails("1", "2", "3");
            BookingQueueDetailsDto bookingDetailsQueueItem = new BookingQueueDetailsDto(bookingDetails, "PROCESS",
                    "Molo Air: Confirming flight <flight number> booked for <name surname> on <flight date>.");
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, bookingDetailsQueueItem);

            return newBooking;
        } catch (Exception e) {
            throw new Exception("Invalid customer or flight id provided");
        }
    }
}
