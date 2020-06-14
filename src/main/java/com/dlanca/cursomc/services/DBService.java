package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.*;
import com.dlanca.cursomc.domain.enums.CustomerType;
import com.dlanca.cursomc.domain.enums.PaymentStatus;
import com.dlanca.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RequestedProductRepository requestedProductRepository;

    public void InstantiateTestDataBase() throws ParseException {
        Category cat1 = new Category(null, "Informatica");
        Category cat2 = new Category(null, "Escritorio");
        Category cat3 = new Category(null, "cama");
        Category cat4 = new Category(null, "mesa");
        Category cat5 = new Category(null, "banho");
        Category cat6 = new Category(null, "jardim");
        Category cat7 = new Category(null, "xablau");

        Product p1 = new Product(null, "computador", 2000.00);
        Product p2 = new Product(null, "impressora", 800.00);
        Product p3 = new Product(null, "mouse", 80.00);
        Product p4 = new Product(null, "mesa de escritorio", 300.00);
        Product p5 = new Product(null, "toalha", 50.00);
        Product p6 = new Product(null, "colcha", 200.00);
        Product p7 = new Product(null, "tv true color", 1200.00);
        Product p8 = new Product(null, "roçadeira", 800.00);
        Product p9 = new Product(null, "abajour", 100.00);
        Product p10 = new Product(null, "pendente", 180.00);
        Product p11 = new Product(null, "shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));


        State s1 = new State(null, "Sao Paulo");
        State s2 = new State(null, "Minas Gerais");


        City c1 = new City(null, "Sao Paulo", s1);
        City c2 = new City(null, "Campina", s1);
        City c3 = new City(null, "Mariana", s2);

        s1.getCities().addAll(Arrays.asList(c1, c2));
        s2.getCities().addAll(Arrays.asList(c3));

        stateRepository.save(Arrays.asList(s1, s2));
        cityRepository.save(Arrays.asList(c1, c2,c3));

        Customer custom = new Customer(null, "maria", "douglasvandersar@gmail.com", "123123123", CustomerType.PERSON);
        custom.getPhoneNumbers().addAll(Arrays.asList("0800", "0900"));

        Address address1 = new Address(null, "rua dos guaranis", "564", "apto 1203", "centro", "30120040", custom, c1);
        Address address2 = new Address(null, "rua dos chavoso", "700", "apto 103", "sem ser o centro", "30120asd", custom, c2);

        custom.getAddress().addAll(Arrays.asList(address1, address2));

        customerRepository.save(Arrays.asList(custom));
        addressRepository.save(Arrays.asList(address1, address2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), custom, address1);
        Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), custom, address2);

        Payment pg1 = new CardPayment(null, PaymentStatus.COMPLETED, order1, 6);
        order1.setPayment(pg1);

        Payment pg2 = new BilletPayment(null, PaymentStatus.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
        order2.setPayment(pg2);

        custom.getOrders().addAll(Arrays.asList(order1, order2));

        orderRepository.save(Arrays.asList(order1, order2));
        paymentRepository.save(Arrays.asList(pg1, pg2));

        RequestedProduct rp1 = new RequestedProduct(order1, p1, 0.00, 1, 2000.00);
        RequestedProduct rp2 = new RequestedProduct(order1, p3, 0.00, 2, 80.00);
        RequestedProduct rp3 = new RequestedProduct(order2, p2, 100.00, 1, 800.00);

        order1.getRequestedProducts().addAll(Arrays.asList(rp1, rp2));
        order2.getRequestedProducts().addAll(Arrays.asList(rp3));

        p1.getRequestedProducts().addAll(Arrays.asList(rp1));
        p2.getRequestedProducts().addAll(Arrays.asList(rp3));
        p3.getRequestedProducts().addAll(Arrays.asList(rp2));

        requestedProductRepository.save(Arrays.asList(rp1, rp2, rp3));
    }
}
