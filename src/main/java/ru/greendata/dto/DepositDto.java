package ru.greendata.dto;

import ru.greendata.entity.Bank;
import ru.greendata.entity.Customer;
import ru.greendata.entity.Deposit;

import java.util.Date;

public class DepositDto {

    private Integer id;

    private Customer customer;

    private Bank bank;

    private Date openDate;

    private double percent;

    private int term;

    Deposit toEntity(){
        Deposit deposit = new Deposit(id, customer, bank, openDate, percent, term);
        return deposit ;
    }
}
