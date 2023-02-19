package com.bbdproj.myjpa.controllers;

import com.bbdproj.myjpa.entities.Staff;
import com.bbdproj.myjpa.entities.StaffRoles;
import com.bbdproj.myjpa.dtos.StaffHelperDTO;
import com.bbdproj.myjpa.repos.StaffRepository;
import com.bbdproj.myjpa.repos.StaffRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/Staff", produces = "application/json")
public class StaffController {

    StaffRepository staffRepository;
    StaffRolesRepository staffRolesRepository;

    public StaffController(StaffRepository staffRepository,
                           StaffRolesRepository staffRolesRepository) {
        this.staffRepository = staffRepository;
        this.staffRolesRepository = staffRolesRepository;
    }

    @GetMapping("/getStaff")
    ResponseEntity<?> getStaffById(@RequestParam(required = true) int staffId) {

        Optional<Staff> staffById = staffRepository.findById(staffId);

        if (staffById.isPresent()) {
            return new ResponseEntity<>(staffById.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/saveNewStaff")
    ResponseEntity<String> saveNewStaff(@RequestBody(required = true) StaffHelperDTO staff) {

        try {
            Optional<StaffRoles> staffRole = staffRolesRepository.findById(staff.getRoleId());

            if (staffRole.isEmpty()) {
                return new ResponseEntity<>("Role of id %s could be found.".formatted(staff.getRoleId()), HttpStatus.NOT_FOUND);
            }

            staffRepository.save(new Staff(staff.getFirstName(), staff.getLastName(), staffRole.get()));

            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
