package com.dlanca.cursomc;

import com.dlanca.cursomc.domain.Category;
import com.dlanca.cursomc.domain.City;
import com.dlanca.cursomc.domain.Product;
import com.dlanca.cursomc.domain.State;
import com.dlanca.cursomc.repositories.CategoryRepository;
import com.dlanca.cursomc.repositories.CityRepository;
import com.dlanca.cursomc.repositories.ProductRepository;
import com.dlanca.cursomc.repositories.StateRepository;
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

    }
}