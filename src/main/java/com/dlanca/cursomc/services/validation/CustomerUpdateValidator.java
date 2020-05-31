package com.dlanca.cursomc.services.validation;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.dto.CustomerDTO;
import com.dlanca.cursomc.dto.NewCustomerDTO;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {
    @Autowired
    private CustomerRepository repo;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(CustomerUpdate ann) { }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Customer aux = repo.findByEmail(objDto.getEmail());
        if(aux != null && ! aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "email ja existete"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty(); }
}
