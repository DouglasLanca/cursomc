package com.dlanca.cursomc.domain;

import com.dlanca.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("billetPayment")
public class BilletPayment extends Payment {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date startDate;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date endDate;

    public BilletPayment() {
    }

    public BilletPayment(Integer id, PaymentStatus status, Order oder, Date startDate, Date endDate) {
        super(id, status, oder);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
