package entelect.training.incubator.controller;

import entelect.training.incubator.dto.CreateBookingDto;
import entelect.training.incubator.dto.FlightDto;
import entelect.training.incubator.dto.SearchByDto;
import entelect.training.incubator.model.Booking;
import entelect.training.incubator.service.BookingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookingById(@PathVariable int id) throws Exception {

        Booking foundEntity = bookingService.findById(id);

        if (foundEntity == null) {
            return ResponseEntity.badRequest().body("Booking not found");
        }
        return new ResponseEntity<>(foundEntity, HttpStatus.OK);
    }

    @GetMapping
    public String getAllBookings() {
        return "All bookings!";
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody @NotNull CreateBookingDto createBookingDto) throws Exception {
        try {
            Booking booking = bookingService.createBooking(createBookingDto);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("search")
    public ResponseEntity<?> searchBookings(@RequestBody SearchByDto searchByDto) {

        Integer customerId = searchByDto.getCustomerId();
        Integer referenceNumber = searchByDto.getReferenceNumber();


        if (customerId != null) {
            return ResponseEntity.ok("Search by customer id");
        }

        if (referenceNumber != null) {
            return ResponseEntity.ok("Search by reference number");
        }

        return ResponseEntity.badRequest().body("Please provide either a customer id or a reference number");
    }


}
