package com.edurbs.delivery.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edurbs.delivery.domain.model.Customer;
import com.edurbs.delivery.domain.model.Delivery;
import com.edurbs.delivery.domain.model.StatusDelivery;
import com.edurbs.delivery.domain.repository.DeliveryRepository;

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
        delivery.setDateOrder(OffsetDateTime.now());

        return deliveryRepository.save(delivery);        
    }

   
}
