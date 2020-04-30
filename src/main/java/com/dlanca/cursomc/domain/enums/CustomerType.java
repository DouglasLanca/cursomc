package com.dlanca.cursomc.domain.enums;

public enum CustomerType {
    PERSON(1, "person cpf"),
    COMPANY(2, "company cnpj");

    private int cod;
    private String description;

    CustomerType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }
    public static CustomerType toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(CustomerType x : CustomerType.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
