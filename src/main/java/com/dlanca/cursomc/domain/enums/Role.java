package com.dlanca.cursomc.domain.enums;

public enum Role {
    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private int cod;
    private String description;

    Role(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public static Role toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (Role x : Role.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }
}
