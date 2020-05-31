package com.dlanca.cursomc.dto;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.services.validation.CustomerUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@CustomerUpdate
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 characteres")
    private String name;

    @NotEmpty(message = "preenchimento obrigatório")
    @Email(message = "email inválido")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
