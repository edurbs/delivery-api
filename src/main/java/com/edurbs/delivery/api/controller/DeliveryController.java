package com.edurbs.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.delivery.api.assembler.DeliveryAssembler;
import com.edurbs.delivery.api.model.DeliveryModel;
import com.edurbs.delivery.api.model.input.DeliveryInput;
import com.edurbs.delivery.domain.repository.DeliveryRepository;
import com.edurbs.delivery.domain.service.AskDeliveryService;
import com.edurbs.delivery.domain.service.CompleteDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private AskDeliveryService askDeliveryService;
    private DeliveryRepository deliveryRepository;
    private DeliveryAssembler deliveryAssembler;
    private CompleteDeliveryService completeDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryModel ask(@Valid @RequestBody DeliveryInput deliveryInput) {
        return deliveryAssembler.toModel(
                askDeliveryService.ask(
                        deliveryAssembler.toEntity(deliveryInput)
                        )
            );
    }

    @GetMapping
    public List<DeliveryModel> list(){
        return deliveryAssembler.toListModel(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryModel> find(@PathVariable Long deliveryId){
        return deliveryRepository.findById(deliveryId)
            .map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
            .orElse(ResponseEntity.notFound().build());       

    }


    @PutMapping("/{deliveryId}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void complete (@PathVariable Long deliveryId){
        completeDeliveryService.complete(deliveryId);
    }


     
}
