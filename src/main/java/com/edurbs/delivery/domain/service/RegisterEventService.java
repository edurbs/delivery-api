package com.edurbs.delivery.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edurbs.delivery.domain.model.Delivery;
import com.edurbs.delivery.domain.model.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterEventService {

    FindDeliveryService findDeliveryService;
    
    @Transactional
    public Event register (Long deliveryId, String description){
        
        Delivery delivery = findDeliveryService.find(deliveryId);
        
        return delivery.addEventDescription(description);       

    }

}
