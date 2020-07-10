package ru.greendata.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer", referencedColumnName ="id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "bank", referencedColumnName ="id", nullable = false)
    private Bank bank;

    @Column(name = "open_date")
    private Date openDate;

    @Column(name = "percent")
    private double percent;

    @Column(name = "term")
    private int term;

    public Deposit() {
    }

    public Deposit(Integer id, Customer customer, Bank bank, Date openDate, double percent, int term) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
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
}
