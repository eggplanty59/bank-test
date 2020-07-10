package ru.greendata.dto;

import ru.greendata.entity.Bank;

public class BankDto {

    private Integer id;
    private String name;
    private String bic;

    Bank toEntity(){
        Bank bank = new Bank(id, name, bic);
        return bank ;
    }
}
