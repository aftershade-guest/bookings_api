package com.bbdproj.myjpa.controllers;

import com.bbdproj.myjpa.dtos.CustomerDTO;
import com.bbdproj.myjpa.entities.Bookings;
import com.bbdproj.myjpa.entities.Customers;
import com.bbdproj.myjpa.entities.Rooms;
import com.bbdproj.myjpa.repos.BookingsRepository;
import com.bbdproj.myjpa.repos.CustomerRepository;
import com.bbdproj.myjpa.repos.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/Bookings", produces = "application/json")
public class BookingsControllers {

    BookingsRepository bookingsRepository;
    CustomerRepository customerRepository;

    RoomRepository roomRepository;

    public BookingsControllers(BookingsRepository bookingsRepository, CustomerRepository customerRepository, RoomRepository repository) {
        this.bookingsRepository = bookingsRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = repository;
    }

    @GetMapping("/allBookings")
    ResponseEntity<?> getAllBookings() {

        Iterable<Bookings> allBookings = bookingsRepository.findAll();

        if (allBookings.iterator().hasNext()) {
            return new ResponseEntity<>(allBookings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("new String()", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/findBookingById")
    ResponseEntity<?> findBookingById(@RequestParam(name = "bookingId", required = true) int bookingId) {

        Optional<Bookings> bookingById = bookingsRepository.findById(bookingId);

        if (bookingById.isPresent()) {
            return new ResponseEntity<>(bookingById, HttpStatus.OK);
        } else {
            return bookingNotFoundError(bookingId);
        }

    }

    @GetMapping("getUserBookings")
    ResponseEntity<Iterable<Bookings>> getBookingByUserId(@RequestParam(name = "userId", required = true) int userId) {
        Optional<Customers> byId = customerRepository.findById(userId);

        Iterable<Bookings> bookings = bookingsRepository.findBycustomerId(byId.get());

        return new ResponseEntity<>(bookings, HttpStatus.OK);

    }

    @PostMapping("/makeBooking")
    ResponseEntity<?> makeBooking(@RequestBody CustomerDTO customerDTO) {


        Optional<Rooms> room = roomRepository.findById(customerDTO.getRoomId());
        if (room.isEmpty() || !room.get().getAvailability()) {
            return new ResponseEntity<>("Room with room number %s is not available".formatted(customerDTO.getRoomId()), HttpStatus.NOT_FOUND);
        }

        if (!customerDTO.checkDate()) {
            return new ResponseEntity<>("Check in date should not be greater than your check out date", HttpStatus.BAD_REQUEST);

        }

        int i = bookingsRepository.uspMakeBooking(customerDTO.getCustomerId(),
                customerDTO.getCheckInDate(),
                customerDTO.getCheckOutDate(), customerDTO.getRoomId());

        Optional<Bookings> byId = bookingsRepository.findById(i);

        return new ResponseEntity<>("Booking successful, your booking reference number is %s".formatted(i), HttpStatus.OK);

    }

    @PatchMapping("/checkIn")
    ResponseEntity<String> checkIn(@RequestParam(value = "bookingId", required = true) int bookingId) {


        if (bookingsRepository.existsById(bookingId)) {
            bookingsRepository.checkUserInByBooking(bookingId);
            return new ResponseEntity<>("", HttpStatus.OK);
        }

        return bookingNotFoundError(bookingId);
    }

    @DeleteMapping("/cancelBooking")
    ResponseEntity<String> cancelBooking(@RequestParam(value = "bookingId", required = true) int bookingId) {

        Optional<Bookings> booking = bookingsRepository.findById(bookingId);


        if (booking.isEmpty()) {
            return bookingNotFoundError(bookingId);
        }

        if (booking.get().isCheckedIn()) {
            return new ResponseEntity<>("Can't cancel booking, user has already checked in.", HttpStatus.FORBIDDEN);

        }

        bookingsRepository.uspChangeStatus(bookingId);
        return new ResponseEntity<>("Booking successfully cancelled.", HttpStatus.OK);

    }

    @PatchMapping("/checkOut")
    ResponseEntity<String> checkOut(@RequestParam(value = "bookingId", required = true) int bookingId) {

        Optional<Bookings> booking = bookingsRepository.findById(bookingId);

        if (booking.isEmpty()) {
            return bookingNotFoundError(bookingId);
        }

        if (!booking.get().isCheckedIn()) {
            return new ResponseEntity<>("Can't check out without checking in.", HttpStatus.BAD_REQUEST);
        }

        bookingsRepository.uspCheckOut(bookingId);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    ResponseEntity<String> bookingNotFoundError(int bookingId) {
        return new ResponseEntity<>("Booking with booking id %s not found.".formatted(bookingId), HttpStatus.NOT_FOUND);
    }

    boolean checkBookingExists(int bookingId) {
        return bookingsRepository.existsById(bookingId);
    }


}
