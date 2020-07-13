package ru.greendata.service;

import org.springframework.stereotype.Service;
import ru.greendata.dto.BankDto;
import ru.greendata.entity.Bank;
import ru.greendata.entity.Deposit;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.repository.BankRepository;
import ru.greendata.repository.DepositRepository;

import java.util.List;

@Service
public class BankService extends BaseService<Bank, BankDto, BankRepository> {

    private final DepositRepository depositRepository;

    private static final int BIC_LENGTH = 9;

    public BankService(BankRepository bankRepository, DepositRepository depositRepository){
        super(bankRepository);
        this.depositRepository = depositRepository;
    }

    @Override
    public BankDto create(BankDto bank){
        if (bank == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if(bank.getBic() == null){
            throw new EntityIllegalArgumentException("БИК не может быть null");
        }
        if(bank.getBic().length() != BIC_LENGTH){
            throw new EntityIllegalArgumentException("БИК должен быть длинной 9 символов");
        }
        return super.create(bank);
    }

    public BankDto update(BankDto bank) {
        if(bank.getBic() == null){
            throw new EntityIllegalArgumentException("БИК не может быть null");
        }
        if(bank.getBic().length() != BIC_LENGTH){
            throw new EntityIllegalArgumentException("БИК должен быть длинной 9 символов");
        }
        return super.update(bank);
    }

    @Override
    public void delete(Object id){
        Bank bank = findById(id);
        List<Deposit> deposits = depositRepository.findByBank(bank);
        if(deposits.size() > 0){
            throw new EntityHasDetailsException(Deposit.TYPE_NAME, bank.getId());
        }
        repository.delete(bank);
    }





}
