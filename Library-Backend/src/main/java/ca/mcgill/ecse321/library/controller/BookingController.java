package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public class BookingController {

    @Autowired
    BookingService bookingService;


    @GetMapping(value = { "/bookings", "/bookings/" })
    public ResponseEntity<?> getAllBookings() {
        try {
            List bookings = bookingService.getAllBookings().stream().map(bk -> DTOConverter.convertToDto(bk, bk.getUser(), bk.getBookingType())).collect(Collectors.toList());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = { "/booking/{name}/itemType/{itemType}/itemId/{itemId}", "/booking/{name}/itemType/{itemType}/itemId/{itemId}/" })
    public ResponseEntity<?> createBooking(@RequestParam String name,@RequestParam String itemType, @RequestParam String itemId) {
        try {
            Booking booking = bookingService.createBooking(name, itemType, itemId);
            return new ResponseEntity<>(DTOConverter.convertToDto(booking, booking.getUser(), booking.getBookingType()), HttpStatus.CREATED) ;
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}