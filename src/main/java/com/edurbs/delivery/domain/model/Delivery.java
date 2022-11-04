package com.edurbs.delivery.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.edurbs.delivery.domain.exception.DomainException;

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

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @Enumerated(EnumType.STRING) //para registrar o nome da ENUM e não os números dos valores delas
    private StatusDelivery status;

    private OffsetDateTime dateOrder; // data iso 8601 com offset

    private OffsetDateTime dateCompleted;

    public Event addEventDescription(String description) {
        Event event = new Event();
        event.setDescription(description);
        event.setDateEvent(OffsetDateTime.now());
        event.setDelivery(this);

        this.getEvents().add(event);
        return event;
    }

    public void complete() {
        if(canNotBeCompleted()){
            throw new DomainException("Entrega não pode ser finalizada.");
        }
        setStatus(StatusDelivery.COMPLETED);
        setDateCompleted(OffsetDateTime.now());
    }

    public boolean canNotBeCompleted(){
        return !getStatus().equals(StatusDelivery.PENDING);
        
    }

    
}
