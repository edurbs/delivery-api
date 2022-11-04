package com.edurbs.delivery.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.edurbs.delivery.domain.model.Recipient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInput {
    
    @Valid
    @NotNull
    private CustomerIdInput customer;

    @Valid
    @NotNull
    private RecipientInput recipient;

    private BigDecimal tax;
}
