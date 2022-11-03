package com.edurbs.delivery.api.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.delivery.api.domain.model.Customer;
import com.edurbs.delivery.api.domain.repository.CustomerRepository;


@RestController
@RequestMapping("/customer")
public class CustomerController {


    private static final String PATH_CUSTOMER_ID = "/{customerId}";    

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<Customer> list(){              
        return customerRepository.findAll();        
    }

    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<Customer> search(@PathVariable Long customerId){        
        return customerRepository.findById(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer add(@Valid @RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<Customer> update(@Valid @PathVariable Long customerId, @RequestBody Customer customer){
        if(!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        customer.setId(customerId);
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @DeleteMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<Void> delete(@PathVariable Long customerId){
        if(!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(customerId);

        return ResponseEntity.noContent().build(); //204 empty without body

    }

}
