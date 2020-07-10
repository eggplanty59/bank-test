package ru.greendata.service;

import org.springframework.stereotype.Service;
import ru.greendata.entity.Deposit;
import ru.greendata.entity.Deposit;
import ru.greendata.repository.DepositRepository;
import ru.greendata.repository.DepositRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService {
    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository){
        this.depositRepository = depositRepository;
    }

    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    public Deposit findById(Object id){
        Optional<Deposit> deposit;
        /*if(id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        Integer parsedId;
        /* try {*/
        parsedId = Integer.valueOf(id.toString());
       /* }catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к нужному типу, текст ошибки: %s", ex));
        }*/
        deposit = depositRepository.findById(parsedId);

        /*if(product == null){
            throw new EntityNotFoundException(Product.TYPE_NAME, parsedId);
        }*/
        return deposit.get();
    }

    public Deposit create(Deposit deposit){
        /*if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }*/
        /*if(product.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        /*Deposit existedDeposit = productRepository.findOne(product.getId());
        if(existedProduct != null){
            throw new EntityAlreadeExistsException(Product.TYPE_NAME, existedProduct.getId());
        }*/
        return depositRepository.save(deposit);
    }

    public Deposit update(Deposit deposit) {
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
        return depositRepository.save(deposit);
    }


    public void delete(Object id){
        Deposit deposit = findById(id);
       /* if(deposit.size() > 0){
            //throw new EntityHasDetailsException(SalesPeriod.TYPE_NAME, product.getId());
        }*/
        depositRepository.delete(deposit);
    }
}
