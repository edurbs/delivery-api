package com.edurbs.delivery.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edurbs.delivery.domain.exception.DomainException;
import com.edurbs.delivery.domain.model.Customer;
import com.edurbs.delivery.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogCustomerService {

    private CustomerRepository customerRepository;

    @Transactional // from Spring
    public Customer save(Customer customer){   

        if (customerRepository.findAllByEmail(customer.getEmail())
                .stream()
                .anyMatch(customerFromDb -> !customerFromDb.equals(customer))
            ) {
            throw new DomainException("Já existe um cliente cadastrado com este email!");
        }
        
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete (Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer find(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(()-> new DomainException("Cliente não encontrado"));
        return customer;
    }
}
