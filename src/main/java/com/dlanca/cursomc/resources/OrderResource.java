package com.dlanca.cursomc.resources;

import com.dlanca.cursomc.domain.Order;
import com.dlanca.cursomc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Order obj = service.searchOrder(id);
        return ResponseEntity.ok().body(obj);
    }
}
