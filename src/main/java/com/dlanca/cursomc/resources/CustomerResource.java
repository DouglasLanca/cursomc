package com.dlanca.cursomc.resources;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Customer obj = service.searchCustomer(id);
        return ResponseEntity.ok().body(obj);
    }
}
