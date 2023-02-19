package com.bbdproj.myjpa.controllers;


import com.bbdproj.myjpa.entities.Rooms;
import com.bbdproj.myjpa.entities.ServiceCatalog;
import com.bbdproj.myjpa.entities.ServiceRequest;
import com.bbdproj.myjpa.dtos.ServiceRequestHelperDTO;
import com.bbdproj.myjpa.repos.RoomRepository;
import com.bbdproj.myjpa.repos.ServiceCatalogRepository;
import com.bbdproj.myjpa.repos.ServiceRequestRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/ServiceRequest", produces = "application/json")
public class ServiceRequestController {

    ServiceRequestRepo serviceRequestRepo;
    RoomRepository roomRepository;

    ServiceCatalogRepository catalogRepository;

    public ServiceRequestController(ServiceRequestRepo serviceRequestRepo,
                                    RoomRepository roomRepository,
                                    ServiceCatalogRepository catalogRepository) {

        this.serviceRequestRepo = serviceRequestRepo;
        this.roomRepository = roomRepository;
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/getAllServices")
    Iterable<ServiceRequest> getAllServiceRequests() {
        Iterable<ServiceRequest> all = serviceRequestRepo.findAll();

        return all;

    }


    @GetMapping("/getServiceById")
    ResponseEntity<?> findSpecificService(int serviceId) {

        Optional<ServiceRequest> serviceRequest = serviceRequestRepo.findById(serviceId);

        if (serviceRequest.isPresent()) {
            return new ResponseEntity<>(serviceRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/findServiceByRoom")
    ResponseEntity<?> findServicesForRoom(int roomId) {

        Optional<Rooms> room = roomRepository.findById(roomId);

        if (room.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        List<ServiceRequest> byroomId = serviceRequestRepo.findByroomId(room.get());

        return new ResponseEntity<>(byroomId, HttpStatus.OK);

    }


    @PostMapping("/makeRequest")
    ResponseEntity<String> makeARequest(@RequestBody ServiceRequestHelperDTO requestHelper) {

        Optional<Rooms> room = roomRepository.findById(requestHelper.getRoomId());
        Optional<ServiceCatalog> catalog = catalogRepository.findById(requestHelper.getServiceId());

        if (room.isEmpty() || !room.get().getAvailability()) {
            return new ResponseEntity<>("Room id provided is either invalid or room is not being occupied by anyone.", HttpStatus.NOT_FOUND);
        }

        if (catalog.isEmpty()) {
            return new ResponseEntity<>("Service id provided is not in list of services provided.", HttpStatus.NOT_FOUND);
        }

        serviceRequestRepo.uspRequestService(requestHelper.getRoomId(), requestHelper.getServiceId(), requestHelper.getProblemdesc());
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }


}
