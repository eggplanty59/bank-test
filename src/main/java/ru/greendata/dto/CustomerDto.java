package ru.greendata.dto;


import ru.greendata.entity.Customer;

import java.io.Serializable;

public class CustomerDto extends BaseDto<Customer> {

    private Integer id;
    private String name;
    private String shortName;
    private String address;
    private String formOfIncorporation;

    public CustomerDto(Integer id, String name, String shortName, String address, String formOfIncorporation) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.formOfIncorporation = formOfIncorporation;
    }

    public CustomerDto() {
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormOfIncorporation() {
        return formOfIncorporation;
    }

    public void setFormOfIncorporation(String formOfIncorporation) {
        this.formOfIncorporation = formOfIncorporation;
    }

    public Customer toEntity(){
        Customer customer = new Customer(id, name, shortName, address, formOfIncorporation);
        return customer ;
    }
}
