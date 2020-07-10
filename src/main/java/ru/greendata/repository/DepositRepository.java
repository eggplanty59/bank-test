package ru.greendata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.greendata.entity.Bank;
import ru.greendata.entity.Customer;
import ru.greendata.entity.Deposit;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    List<Deposit> findByBank(Bank bank);
    List<Deposit> findByCustomer(Customer customer);
}
