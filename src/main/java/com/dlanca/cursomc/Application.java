package com.dlanca.cursomc;

import com.dlanca.cursomc.domain.*;
import com.dlanca.cursomc.domain.enums.CustomerType;
import com.dlanca.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Informatica");
        Category cat2 = new Category(null, "Escritorio");

        Product p1 = new Product(null, "computador", 2000.00);
        Product p2 = new Product(null, "impressora", 800.00);
        Product p3 = new Product(null, "mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.save(Arrays.asList(cat1, cat2));
        productRepository.save(Arrays.asList(p1, p2, p3));


        State s1 = new State(null, "Sao Paulo");
        State s2 = new State(null, "Minas Gerais");


        City c1 = new City(null, "Sao Paulo", s1);
        City c2 = new City(null, "Campina", s1);
        City c3 = new City(null, "Mariana", s2);

        s1.getCities().addAll(Arrays.asList(c1, c2));
        s2.getCities().addAll(Arrays.asList(c3));

        stateRepository.save(Arrays.asList(s1, s2));
        cityRepository.save(Arrays.asList(c1, c2,c3));

        Customer custom = new Customer(null, "maria", "maria@gmail", "123123123", CustomerType.PERSON);
        custom.getPhoneNumbers().addAll(Arrays.asList("0800", "0900"));

        Address address1 = new Address(null, "rua dos guaranis", "564", "apto 1203", "centro", "30120040", custom, c1);
        Address address2 = new Address(null, "rua dos chavoso", "700", "apto 103", "sem ser o centro", "30120asd", custom, c2);

        custom.getAddress().addAll(Arrays.asList(address1, address2));

        customerRepository.save(Arrays.asList(custom));
        addressRepository.save(Arrays.asList(address1, address2));

    }
}