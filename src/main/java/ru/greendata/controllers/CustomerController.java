package ru.greendata.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.greendata.dto.CustomerDto;
import ru.greendata.entity.Customer;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.service.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController extends BaseController<CustomerDto, Customer, CustomerRepository>{
    public CustomerController(CustomerService service) {
        super(service);
    }
}
