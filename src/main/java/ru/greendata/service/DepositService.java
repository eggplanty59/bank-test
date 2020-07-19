package ru.greendata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greendata.dto.DepositDto;
import ru.greendata.entity.Deposit;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.repository.BankRepository;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.repository.DepositRepository;

@Service
public class DepositService extends BaseService<Deposit, DepositDto, DepositRepository> implements IService<Deposit, DepositDto, DepositRepository>{

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, BankRepository bankRepository, CustomerRepository customerRepository){
        super(depositRepository);
        this.bankRepository = bankRepository;
        this.customerRepository = customerRepository;
    }


    public DepositService(){
        bankRepository = null;
        customerRepository = null;
    }

    public DepositDto create(DepositDto deposit){
        check(deposit);
        return  super.create(deposit);
    }

    public DepositDto update(DepositDto deposit) {
        check(deposit);
        return super.update(deposit);
    }

    @Override
    public void delete(Object id){
        Deposit deposit = findById(id);
        repository.delete(deposit);
    }

    private void check(DepositDto deposit) {
        if(deposit == null){
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if(deposit.getBank() == null){
            throw new EntityIllegalArgumentException("Банк не может быть null");
        }
        if(deposit.getBank().getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор банка не может быть null");
        }
        if(!bankRepository.findById(deposit.getBank().getId()).isPresent()){
            throw new EntityIllegalArgumentException("Банка с таким идентификатором не существует");
        }
        if(deposit.getCustomer() == null){
            throw new EntityIllegalArgumentException("Клиент не может быть null");
        }
        if(deposit.getCustomer().getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор клиента не может быть null");
        }
        if(!customerRepository.findById(deposit.getCustomer().getId()).isPresent()){
            throw new EntityIllegalArgumentException("Клиента с таким идентификатором не существует");
        }
        if(deposit.getOpenDate() == null){
            throw new EntityIllegalArgumentException("Дата открытия не может быть null");
        }
    }
}
