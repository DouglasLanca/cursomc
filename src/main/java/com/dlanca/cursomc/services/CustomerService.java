package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer searchCustomer(Integer id){
        Customer obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                    + ", Tipo: " + Customer.class.getName());
        }
        return obj;
    }
}