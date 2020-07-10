package ru.greendata.dto.params;

import ru.greendata.entity.Bank;
import ru.greendata.entity.Customer;

import java.util.Date;

public class DepositParams {

    private Integer id;

    private Customer customer;

    private Bank bank;

    private Date openDate;

    private double percent;

    private int term;

    private String orderBy;
    private String orderDir;
}
