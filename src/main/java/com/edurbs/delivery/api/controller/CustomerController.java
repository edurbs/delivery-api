package com.edurbs.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.edurbs.delivery.api.assembler.CustomerAssembler;
import com.edurbs.delivery.api.model.CustomerModel;
import com.edurbs.delivery.api.model.input.CustomerInput;
import com.edurbs.delivery.domain.model.Customer;
import com.edurbs.delivery.domain.repository.CustomerRepository;
import com.edurbs.delivery.domain.service.CatalogCustomerService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    
    private static final String PATH_CUSTOMER_ID = "/{customerId}";    

    private CustomerRepository customerRepository;
    private CatalogCustomerService catalogCustomerService;
    private CustomerAssembler customerAssembler;

    @GetMapping("/")
    public List<Customer> list(){              
        return customerRepository.findAll();        
    }

    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<CustomerModel> search(@PathVariable Long customerId){        
        return customerRepository.findById(customerId)
                .map(customer -> ResponseEntity.ok(customerAssembler.toModel(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerModel add(@Valid @RequestBody CustomerInput customerInput){ 
        return customerAssembler.toModel(
            catalogCustomerService.save(
                customerAssembler.toEntity(customerInput)
            )
        );        
    }

    @PutMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<CustomerInput> update(@Valid @PathVariable Long customerId, @RequestBody CustomerInput customerInput){
        if(!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        Customer customer = customerAssembler.toEntity(customerInput);
        customer.setId(customerId);
        catalogCustomerService.save(customer);
        return ResponseEntity.ok(customerInput);
    }

    @DeleteMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<Void> delete(@PathVariable Long customerId){
        if(!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        catalogCustomerService.delete(customerId);

        return ResponseEntity.noContent().build(); //204 empty without body

    }

}
