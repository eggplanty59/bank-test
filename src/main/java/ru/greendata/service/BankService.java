package ru.greendata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.greendata.dto.filters.BankSpecification;
import ru.greendata.dto.filters.FilterCriteria;
import ru.greendata.dto.params.BankParams;
import ru.greendata.dto.params.Page;
import ru.greendata.dto.params.PageParams;
import ru.greendata.entity.Bank;
import ru.greendata.entity.Deposit;
import ru.greendata.repository.BankRepository;
import ru.greendata.repository.DepositRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final BankRepository bankRepository;

    private final DepositRepository depositRepository;

    public BankService(BankRepository bankRepository, DepositRepository depositRepository){
        this.bankRepository = bankRepository;
        this.depositRepository = depositRepository;
    }

    public List<Bank> findAll(){
        return bankRepository.findAll();
    }

    public Bank findById(Object id){
        Optional<Bank> bank;
        /*if(id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        Integer parsedId;
       /* try {*/
            parsedId = Integer.valueOf(id.toString());
       /* }catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к нужному типу, текст ошибки: %s", ex));
        }*/
        bank = bankRepository.findById(parsedId);

        /*if(product == null){
            throw new EntityNotFoundException(Product.TYPE_NAME, parsedId);
        }*/
        return bank.get();
    }

    public Bank create(Bank bank){
        /*if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }*/
        /*if(product.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }*/
        /*Bank existedBank = productRepository.findOne(product.getId());
        if(existedProduct != null){
            throw new EntityAlreadeExistsException(Product.TYPE_NAME, existedProduct.getId());
        }*/
        return bankRepository.save(bank);
    }

    public Bank update(Bank bank) {
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
        return bankRepository.save(bank);
    }


    public void delete(Object id){
        Bank bank = findById(id);
        List<Deposit> salesPeriods = depositRepository.findByBank(bank);
        if(salesPeriods.size() > 0){
            //throw new EntityHasDetailsException(SalesPeriod.TYPE_NAME, product.getId());
        }
        bankRepository.delete(bank);
    }


    public Page<Bank> list(PageParams<BankParams> pageParams) {
        final BankParams params = pageParams.getParams() == null ? new BankParams() : pageParams.getParams();

        Long count = bankRepository.count();

        List<Bank> list = bankRepository.findAll(getBankSelect(params), getOrderBy(params));

        return new Page<Bank>(list, count);
    }

    private Specification<Bank> getBankSelect(BankParams params){
        Specification spec = null;
        List<FilterCriteria> listFilterCriteria = params.getFilterCriteria();
        if (listFilterCriteria == null){
            return null;
        }
        for (FilterCriteria filterCriteria: listFilterCriteria){
            BankSpecification bankSpecification = new BankSpecification(filterCriteria);
            spec = spec == null ? Specification.where(bankSpecification) : spec.and(bankSpecification);
        }
        return spec;
    }

    private Sort getOrderBy(BankParams params){
        boolean asc = params.getOrderDir() != null && params.getOrderDir().equalsIgnoreCase("asc");
        Sort.Direction sortDir = asc ? Sort.Direction.ASC: Sort.Direction.DESC;

        if (params.getOrderBy() == null){
            return Sort.by(sortDir, "id");
        }

        Sort sort = null;
        String[] orderArray = params.getOrderBy().split(",");

        for (String order: orderArray){
            if (order.equalsIgnoreCase("id")){
                sort = sort == null ? (Sort.by(sortDir, "id")) : sort.and(Sort.by(sortDir, "id"));
            }
            if (order.equalsIgnoreCase("name")){
                sort = sort == null ? (Sort.by(sortDir, "name")) : sort.and(Sort.by(sortDir, "name"));
            }
            if (order.equalsIgnoreCase("bic")) {
                sort = sort == null ? (Sort.by(sortDir, "bic")) : sort.and(Sort.by(sortDir, "bic"));
            }
        }

        return sort;
    }
}
