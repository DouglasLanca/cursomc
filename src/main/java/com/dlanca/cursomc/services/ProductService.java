package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Category;
import com.dlanca.cursomc.domain.Order;
import com.dlanca.cursomc.domain.Product;
import com.dlanca.cursomc.repositories.CategoryRepository;
import com.dlanca.cursomc.repositories.ProductRepository;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id){
        Product obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                    + ", Tipo: " + Order.class.getName());
        }
        return obj;
    }

    public Page<Product> search(String name, List<Integer> categoryIds, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAll(categoryIds);
        return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}