package com.dlanca.cursomc.domain.enums;

public enum PaymentStatus {
    PENDING(1, "Payment is pending"),
    COMPLETED(2, "Payment is completed"),
    CANCELED(3, "Payment is canceled");

    private int cod;
    private String description;

    PaymentStatus(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public static PaymentStatus toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (PaymentStatus x : PaymentStatus.values()){
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
