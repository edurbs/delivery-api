package com.edurbs.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.delivery.api.assembler.EventAssembler;
import com.edurbs.delivery.api.model.EventModel;
import com.edurbs.delivery.api.model.input.EventInput;
import com.edurbs.delivery.domain.model.Delivery;
import com.edurbs.delivery.domain.model.Event;
import com.edurbs.delivery.domain.service.FindDeliveryService;
import com.edurbs.delivery.domain.service.RegisterEventService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery/{deliveryId}/event")
public class EventController {
    
    private RegisterEventService registerEventService;
    private EventAssembler eventAssembler;
    private FindDeliveryService findDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventModel register (@PathVariable Long deliveryId, @Valid @RequestBody EventInput eventInput){
        Event eventRegistred = registerEventService.register(deliveryId, eventInput.getDescription()); 
        
        return eventAssembler.toModel(eventRegistred);
    }

    @GetMapping
    public List<EventModel> list(@PathVariable Long deliveryId){
        return eventAssembler.toListModel(findDeliveryService.find(deliveryId).getEvents());
    }
}
