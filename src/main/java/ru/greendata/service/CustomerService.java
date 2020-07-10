package ru.greendata.service;

import org.springframework.stereotype.Service;
import ru.greendata.entity.Customer;
import ru.greendata.entity.Deposit;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.repository.DepositRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final DepositRepository depositRepository;

    public CustomerService(CustomerRepository customerRepository, DepositRepository depositRepository){
        this.customerRepository = customerRepository;
        this.depositRepository = depositRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Object id){
        Optional<Customer> customer;
        /*if(id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        Integer parsedId;
        /* try {*/
        parsedId = Integer.valueOf(id.toString());
       /* }catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к нужному типу, текст ошибки: %s", ex));
        }*/
        customer = customerRepository.findById(parsedId);

        /*if(product == null){
            throw new EntityNotFoundException(Product.TYPE_NAME, parsedId);
        }*/
        return customer.get();
    }

    public Customer create(Customer customer){
        /*if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }*/
        /*if(product.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        /*Customer existedCustomer = productRepository.findOne(product.getId());
        if(existedProduct != null){
            throw new EntityAlreadeExistsException(Product.TYPE_NAME, existedProduct.getId());
        }*/
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        /*if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }*/
        /*if(product.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        /*Product existedProduct = productRepository.findOne(product.getId());
        if(existedProduct == null){
            throw new EntityNotFoundException(Product.TYPE_NAME, product.getId());
        }*/
        return customerRepository.save(customer);
    }


    public void delete(Object id){
        Customer customer = findById(id);
        List<Deposit> salesPeriods = depositRepository.findByCustomer(customer);
        if(salesPeriods.size() > 0){
            //throw new EntityHasDetailsException(SalesPeriod.TYPE_NAME, product.getId());
        }
        customerRepository.delete(customer);
    }
}
