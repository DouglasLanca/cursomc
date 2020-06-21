package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Address;
import com.dlanca.cursomc.domain.City;
import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.domain.enums.CustomerType;
import com.dlanca.cursomc.domain.enums.Role;
import com.dlanca.cursomc.dto.CustomerDTO;
import com.dlanca.cursomc.dto.NewCustomerDTO;
import com.dlanca.cursomc.repositories.AddressRepository;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.security.UserSpringSecurity;
import com.dlanca.cursomc.services.exceptions.AuthorizationException;
import com.dlanca.cursomc.services.exceptions.DataIntegrityException;
import com.dlanca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private AddressRepository addressRepository;


    public Customer find(Integer id){

        UserSpringSecurity user = UserService.authenticated();
        if (user ==null || !user.hasRole(Role.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado");
        }

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
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Customer fromDTO(NewCustomerDTO objDto){
        Customer customer = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), CustomerType.toEnum(objDto.getCustomerType()), bCryptPasswordEncoder.encode(objDto.getPassword()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getCep(), customer, city);
        customer.getAddress().add(address);
        customer.getPhoneNumbers().add(objDto.getPhone1());
        if (objDto.getPhone2() != null){
            customer.getPhoneNumbers().add(objDto.getPhone2());
        }
        if (objDto.getPhone3() != null){
            customer.getPhoneNumbers().add(objDto.getPhone3());
        }
        return customer;
    }

    @Transactional
    public Customer insert(Customer obj){
        obj.setId(null);
        obj = repo.save(obj);
        addressRepository.save(obj.getAddress());
        return obj;
    }
}