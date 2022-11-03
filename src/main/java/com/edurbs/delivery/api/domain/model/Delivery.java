package com.edurbs.delivery.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Embedded // os dados vão ficar dentro da tabela e não numa tabela separada
    private Recipient recipient;

    private BigDecimal tax;

    @Enumerated(EnumType.STRING) //para registrar o nome da ENUM e não os números dos valores delas
    @JsonProperty(access = Access.READ_ONLY)
    private StatusDelivery status;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dateOrder;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dateCompleted;

    
}
