package com.edurbs.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edurbs.delivery.api.model.EventModel;
import com.edurbs.delivery.domain.model.Event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EventAssembler {
    
    private ModelMapper modelMapper;

    public EventModel toModel(Event event){
        return modelMapper.map(event, EventModel.class);
    }

    public List<EventModel> toListModel (List<Event> listEvents){
        return listEvents.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
