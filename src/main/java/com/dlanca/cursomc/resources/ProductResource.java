package com.dlanca.cursomc.resources;

import com.dlanca.cursomc.domain.Product;
import com.dlanca.cursomc.dto.ProductDTO;
import com.dlanca.cursomc.resources.utils.URL;
import com.dlanca.cursomc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction){
        Page<Product> list = service.search(URL.decodeParam(name), URL.decodeIntList(categories), page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
}
