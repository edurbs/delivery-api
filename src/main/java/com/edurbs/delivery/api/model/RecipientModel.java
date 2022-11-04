package com.edurbs.delivery.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientModel {

    private String name;
    private String address;
    private String number;
    private String complement;
    private String locality;
}
