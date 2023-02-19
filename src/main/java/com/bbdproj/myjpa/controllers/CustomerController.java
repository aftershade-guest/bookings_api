package com.bbdproj.myjpa.controllers;


import com.bbdproj.myjpa.entities.Customers;
import com.bbdproj.myjpa.repos.CustomerRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/Customer")
public class CustomerController {

    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{userId}")
    ResponseEntity<?> getUserById(@PathParam("userId") int userId) {

        Optional<Customers> optionalCustomers = customerRepository.findById(userId);

        if (optionalCustomers.isPresent()) {
            return new ResponseEntity<>(optionalCustomers.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user with id %s found. Please check if the user id provided is correct.".formatted(userId), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/email/{email}")
    ResponseEntity<?> getUserByEmail(@PathParam("email") String email) {

        Optional<Customers> optionalCustomers = customerRepository.findCustomersByEmail(email);

        if (optionalCustomers.isPresent()) {
            return new ResponseEntity<>(optionalCustomers.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user with id %s found. Please check if the user id provided is correct.".formatted(email), HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping
    ResponseEntity<String> saveNewUser( @RequestBody(required = true) Customers customers) {

        try {

            customerRepository.save(new Customers(customers.getFirstName(), customers.getLastName(), customers.getEmail()));

            return new ResponseEntity<>("User saved.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }


}
