package com.edurbs.delivery.api.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edurbs.delivery.api.domain.exception.DomainException;
import com.edurbs.delivery.api.domain.model.Customer;
import com.edurbs.delivery.api.domain.model.Delivery;
import com.edurbs.delivery.api.domain.model.StatusDelivery;
import com.edurbs.delivery.api.domain.repository.CustomerRepository;
import com.edurbs.delivery.api.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AskDeliveryService {

    private DeliveryRepository deliveryRepository;
    private CatalogCustomerService catalogCustomerService;
    
    @Transactional
    public Delivery ask(Delivery delivery){

        Customer customer = catalogCustomerService.find(delivery.getCustomer().getId());
        
        delivery.setCustomer(customer);
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setDateOrder(LocalDateTime.now());

        return deliveryRepository.save(delivery);        
    }

   
}
