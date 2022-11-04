package com.edurbs.delivery.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventModel {

    private Long id;
    private String description;
    private OffsetDateTime dateEvent;
    
}
