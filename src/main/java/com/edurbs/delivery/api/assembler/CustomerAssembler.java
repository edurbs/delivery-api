package com.edurbs.delivery.api.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edurbs.delivery.api.model.CustomerModel;
import com.edurbs.delivery.api.model.input.CustomerInput;
import com.edurbs.delivery.domain.model.Customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomerAssembler {
    
    private ModelMapper modelMapper;

    public CustomerModel toModel(Customer customer){
        return modelMapper.map(customer, CustomerModel.class);        
    }

    public CustomerModel toModel(Optional<Customer> customer){
        if(customer.isPresent()){
            return modelMapper.map(customer, CustomerModel.class);
        }

        return null;        
    }

    public List<CustomerModel> toListModel(List<Customer> customers){
        return customers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Customer toEntity(CustomerInput customerInput){
        return modelMapper.map(customerInput, Customer.class);
    }
}
