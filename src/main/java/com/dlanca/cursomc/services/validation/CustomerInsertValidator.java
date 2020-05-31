package com.dlanca.cursomc.services.validation;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.dto.NewCustomerDTO;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {
    @Autowired
    private CustomerRepository repo;

    @Override
    public void initialize(CustomerInsert ann) { }

    @Override
    public boolean isValid(NewCustomerDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //TODO IMPLEMENTS THE LOG FOR CNPJ CPF

        Customer aux = repo.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "email ja existete"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty(); }
}
