package com.dlanca.cursomc.domain;

import com.dlanca.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment{
    private static final long serialVersionUID = 1L;
    private Integer numberOfParcels;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentStatus status, Order oder, Integer numberOfParcels) {
        super(id, status, oder);
        this.numberOfParcels = numberOfParcels;
    }

    public Integer getNumberOfParcels() {
        return numberOfParcels;
    }

    public void setNumberOfParcels(Integer numberOfParcels) {
        this.numberOfParcels = numberOfParcels;
    }
}
