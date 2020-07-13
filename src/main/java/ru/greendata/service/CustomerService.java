package ru.greendata.service;

import org.springframework.stereotype.Service;
import ru.greendata.dto.CustomerDto;
import ru.greendata.entity.Customer;
import ru.greendata.entity.Deposit;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.repository.DepositRepository;

import java.util.List;

@Service
public class CustomerService extends BaseService<Customer, CustomerDto, CustomerRepository> {

    private final DepositRepository depositRepository;

    public CustomerService(CustomerRepository customerRepository, DepositRepository depositRepository){
        super(customerRepository);
        this.depositRepository = depositRepository;
    }

    @Override
    public CustomerDto create(CustomerDto customer){
        if (customer == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        return super.create(customer);
    }

    @Override
    public void delete(Object id){
        Customer customer = findById(id);
        List<Deposit> deposits = depositRepository.findByCustomer(customer);
        if(deposits.size() > 0){
            throw new EntityHasDetailsException(Deposit.TYPE_NAME, customer.getId());
        }
        repository.delete(customer);
    }
}
