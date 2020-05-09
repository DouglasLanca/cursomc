package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Category;
import com.dlanca.cursomc.repositories.CategoryRepository;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id){
        Category obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                + ", Tipo: " + Category.class.getName());
        }
        return obj;
    }

    public Category insert(Category obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        return repo.save(obj);
    }
}
