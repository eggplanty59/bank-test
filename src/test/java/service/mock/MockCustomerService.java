package service.mock;

import org.springframework.stereotype.Service;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Customer;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.service.BaseService;
import ru.greendata.service.CustomerService;

import java.util.Arrays;
import java.util.List;

@Service
public class MockCustomerService extends CustomerService {

    public MockCustomerService() {
    }

    @Override
    public CustomerDto create(CustomerDto customer){
        return customer;
    }

    public CustomerDto update(CustomerDto customer) {
        return customer;
    }

    @Override
    public void delete(Object id){
    }

    @Override
    public List<CustomerDto> list(Params params){
        return Arrays.asList(new CustomerDto[]{new CustomerDto(1, "name", "shortName","address","form")});
    }
}
