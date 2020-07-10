package ru.greendata.dto;


import ru.greendata.entity.Bank;
import ru.greendata.entity.Customer;

public class CustomerDto {

    private Integer id;

    private String name;

    private String shortName;

    private String address;

    private String formOfIncorporation;

    Customer toEntity(){
        Customer customer = new Customer(id, name, shortName, address, formOfIncorporation);
        return customer ;
    }
}
