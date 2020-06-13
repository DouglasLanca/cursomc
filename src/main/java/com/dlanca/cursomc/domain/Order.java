package com.dlanca.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="ORDER_TABLE")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "addres_id")
    private Address deliveryAddres;

//    items
    @OneToMany(mappedBy = "id.order")
    private Set<RequestedProduct> requestedProducts = new HashSet<>();

    public Order() {
    }

    public Order(Integer id, Date date, Customer customer, Address deliveryAddres) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.deliveryAddres = deliveryAddres;
    }

    public double getTotalValue(){
        double sum = 0.0;
        for (RequestedProduct requestedProduct : requestedProducts){
            sum = sum + requestedProduct.getSubTotal();
        }
        return sum;
    }

    public Set<RequestedProduct> getRequestedProducts() {
        return requestedProducts;
    }

    public void setRequestedProducts(Set<RequestedProduct> requestedProducts) {
        this.requestedProducts = requestedProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddres() {
        return deliveryAddres;
    }

    public void setDeliveryAddres(Address deliveryAddres) {
        this.deliveryAddres = deliveryAddres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getDate()));
        builder.append(", Cliente: ");
        builder.append(getCustomer().getName());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getStatus().getDescription());
        builder.append("\nDetalhes:\n");
        for (RequestedProduct ip : getRequestedProducts()) {
            builder.append(ip.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getTotalValue()));
        return builder.toString();
    }
}
