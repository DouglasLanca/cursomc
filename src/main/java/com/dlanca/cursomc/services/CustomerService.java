package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.dto.CustomerDTO;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.services.exceptions.DataIntegrityException;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;


    public Customer find(Integer id){
        Customer obj = repo.findOne(id);
        if (obj == null){
            throw new ObjectNotFoundException("Object not found: "+ id
                    + ", Tipo: " + Customer.class.getName());
        }
        return obj;
    }

    public Customer update(Customer obj){
        Customer newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Customer newObj, Customer obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.delete(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Não é possivel excluir clientes porque tem uns trem ai");
        }
    }

    public List<Customer> findAll(){
        return repo.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Customer fromDTO(CustomerDTO objDto){
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }
}