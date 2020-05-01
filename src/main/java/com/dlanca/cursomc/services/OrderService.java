package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Order;
import com.dlanca.cursomc.repositories.OrderRepository;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public Order searchOrder(Integer id){
        Order obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                    + ", Tipo: " + Order.class.getName());
        }
        return obj;
    }
}