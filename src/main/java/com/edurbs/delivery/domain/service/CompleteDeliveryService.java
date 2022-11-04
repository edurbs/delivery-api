package com.edurbs.delivery.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edurbs.delivery.domain.model.Delivery;
import com.edurbs.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompleteDeliveryService {
    
    private DeliveryRepository deliveryRepository;
    private FindDeliveryService findDeliveryService;

    @Transactional
    public void complete (Long deliveryId){
        Delivery delivery = findDeliveryService.find(deliveryId);

        delivery.complete();

        deliveryRepository.save(delivery);
    }
}
