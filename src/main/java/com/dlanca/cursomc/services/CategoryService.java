package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Category;
import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.dto.CategoryDTO;
import com.dlanca.cursomc.repositories.CategoryRepository;
import com.dlanca.cursomc.services.exceptions.DataIntegrityException;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.*;

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
        Category newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.delete(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Não é possivel excluir categorias que possuem produtos");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getName());
    }
}
