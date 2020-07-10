package ru.greendata.entity;

import javax.persistence.*;

@Entity
@Table(name ="bank")
public class Bank {

    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "bic")
    private String bic;

    public Bank() {
    }

    public Bank(Integer id, String name, String bic){
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

    public void setBic(String bankIdCode) {
        this.bic = bankIdCode;
    }
}
