package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.BilletPayment;
import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.domain.Order;
import com.dlanca.cursomc.domain.RequestedProduct;
import com.dlanca.cursomc.domain.enums.PaymentStatus;
import com.dlanca.cursomc.repositories.OrderRepository;
import com.dlanca.cursomc.repositories.PaymentRepository;
import com.dlanca.cursomc.repositories.RequestedProductRepository;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private BilletPaymentService billetPaymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RequestedProductRepository requestedProductRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    public Order searchOrder(Integer id){
        Order obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                    + ", Tipo: " + Order.class.getName());
        }
        return obj;
    }

    @Transactional
    public Order insert(Order obj){
        obj.setId(null);
        obj.setDate(new Date());
        obj.getPayment().setStatus(PaymentStatus.PENDING);
        obj.getPayment().setOrder(obj);
        if(obj.getPayment() instanceof BilletPayment){
            BilletPayment payment = (BilletPayment) obj.getPayment();
            billetPaymentService.fillBilletPayment(payment, obj.getDate());
        }
        obj.setCustomer(customerService.find(obj.getCustomer().getId()));

        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for (RequestedProduct product: obj.getRequestedProducts()){
            product.setDiscount(0.0);
            product.setProduct(productService.find(product.getProduct().getId()));
            product.setPrice(product.getProduct().getPrice());
            product.setOrder(obj);
        }
        requestedProductRepository.save(obj.getRequestedProducts());
        emailService.sendOrderConfirmationEmail(obj);
        System.out.println(obj);
        return obj;
    }
}