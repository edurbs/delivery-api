package com.edurbs.delivery.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventInput {
    
    @NotBlank
    private String description;
}
