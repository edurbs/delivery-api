package com.edurbs.delivery.domain.service;

import org.springframework.stereotype.Service;

import com.edurbs.delivery.domain.exception.DomainException;
import com.edurbs.delivery.domain.exception.EntityNotFoundException;
import com.edurbs.delivery.domain.model.Delivery;
import com.edurbs.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FindDeliveryService {

    private DeliveryRepository deliveryRepository;
    
    public Delivery find(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(()-> new EntityNotFoundException("Entrega não encontrada"));
        
    }
}
