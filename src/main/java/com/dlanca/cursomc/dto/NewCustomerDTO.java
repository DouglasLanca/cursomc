package com.dlanca.cursomc.dto;

import com.dlanca.cursomc.services.validation.CustomerInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@CustomerInsert
public class NewCustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 characteres")
    private String name;

    @NotEmpty(message = "preenchimento obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotEmpty(message = "preenchimento obrigatório")
    private String password;

    @NotEmpty(message = "preenchimento obrigatório")
    private String cpfOrCnpj;
    private Integer customerType;

    @NotEmpty(message = "preenchimento obrigatório")
    private String street;

    @NotEmpty(message = "preenchimento obrigatório")
    private String number;
    private String complement;
    private String neighborhood;

    @NotEmpty(message = "preenchimento obrigatório")
    private String cep;

    @NotEmpty(message = "preenchimento obrigatório")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public NewCustomerDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
