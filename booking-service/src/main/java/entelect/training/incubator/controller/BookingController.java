package entelect.training.incubator.controller;

import entelect.training.incubator.dto.SearchByDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @GetMapping("{id}")
    public String getBookingById(@PathVariable int id) {
        return "Booking " + id;
    }

    @GetMapping
    public String getAllBookings() {
        return "All bookings!";
    }

    @PostMapping
    public ResponseEntity<?> createBooking() {
        return ResponseEntity.ok("Booking");
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
