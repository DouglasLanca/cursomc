package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Category;
import com.dlanca.cursomc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category searchCategory(Integer id){
        Category obj = repo.findOne(id);
        return obj;
    }
}
