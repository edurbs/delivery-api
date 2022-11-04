package com.edurbs.delivery.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edurbs.delivery.domain.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByNameContaining(String name);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAllByEmail(String email);
}
