package com.edurbs.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edurbs.delivery.api.model.DeliveryModel;
import com.edurbs.delivery.api.model.input.DeliveryInput;
import com.edurbs.delivery.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryAssembler {
    
    private ModelMapper modelMapper;

    public DeliveryModel toModel(Delivery delivery){
        return modelMapper.map(delivery, DeliveryModel.class);
    }

    public List<DeliveryModel> toListModel(List<Delivery> deliveries){
        return deliveries.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryInput deliveryInput){
        return modelMapper.map(deliveryInput, Delivery.class);
    }
}
