package com.edurbs.delivery.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.edurbs.delivery.domain.model.StatusDelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {
    
    private Long id;
    private CustomerDeliveryModel customer;
    private RecipientModel recipient;
    private BigDecimal tax;
    private StatusDelivery status;
    private OffsetDateTime dateOrder;
    private OffsetDateTime dateCompleted;

}
