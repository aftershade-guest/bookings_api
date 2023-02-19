package com.bbdproj.myjpa.repos;

import com.bbdproj.myjpa.entities.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customers, Integer> {

    Optional<Customers> findCustomersByEmail(String email);

}
