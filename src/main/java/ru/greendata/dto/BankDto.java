package ru.greendata.dto;

import ru.greendata.entity.Bank;

import java.io.Serializable;

public class BankDto extends BaseDto<Bank> {

    private Integer id;
    private String name;
    private String bic;

    public BankDto() {
    }

    public BankDto(Integer id, String name, String bic) {
        this.id = id;
        this.name = name;
        this.bic = bic;
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

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Bank toEntity(){
        Bank bank = new Bank(id, name, bic);
        return bank ;
    }
}
