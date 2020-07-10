package ru.greendata.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.greendata.entity.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {
    @Override
    List<Bank> findAll(Sort sort);


}
