package ru.greendata.entity;

import ru.greendata.dto.CustomerDto;

import javax.persistence.*;

@Entity
@Table(name = "customer")

public class Customer extends BaseEntity<CustomerDto> {
    public static String TYPE_NAME = "Клиент";

    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "address")
    private String address;

    @Column(name = "form_of_incorporation")
    private String formOfIncorporation;

    public Customer() {
    }

    public Customer(Integer id, String name, String shortName, String address,String formOfIncorporation ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.formOfIncorporation = formOfIncorporation;
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

    public CustomerDto toDto(){
        return new CustomerDto(id, name, shortName, address, formOfIncorporation);
    }
}
