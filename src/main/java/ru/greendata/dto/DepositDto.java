package ru.greendata.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.greendata.entity.Deposit;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositDto extends BaseDto<Deposit> implements Serializable {

    private Integer id;
    private CustomerDto customer;
    private BankDto bank;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date openDate;
    private double percent;
    private int term;

    public DepositDto() {
    }

    public DepositDto(Integer id, CustomerDto customer, BankDto bank, Date openDate, double percent, int term) {
        this.id = id;
        this.customer = customer;
        this.bank = bank;
        this.openDate = openDate;
        this.percent = percent;
        this.term = term;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public BankDto getBank() {
        return bank;
    }

    public void setBank(BankDto bank) {
        this.bank = bank;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Deposit toEntity(){
        Deposit deposit = new Deposit(id, customer.toEntity(), bank.toEntity(), openDate, percent, term);
        return deposit ;
    }
}
