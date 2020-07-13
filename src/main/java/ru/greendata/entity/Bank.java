package ru.greendata.entity;

import ru.greendata.dto.BankDto;

import javax.persistence.*;


@Entity
@Table(name ="bank")
public class Bank extends BaseEntity<BankDto> {
    public static String TYPE_NAME = "Банк";

    public static void setTypeName(String typeName) {
        TYPE_NAME = typeName;
    }

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

    public BankDto toDto(){
        return new BankDto(id, name, bic);
    }
}
