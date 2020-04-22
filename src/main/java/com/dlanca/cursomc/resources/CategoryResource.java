package com.dlanca.cursomc.resources;

import com.dlanca.cursomc.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> listing(){

        Category cat1 = new Category(1, "informatica");
        Category cat2 = new Category(2, "escritorio");

        List<Category> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);
        return lista;
    }
}
