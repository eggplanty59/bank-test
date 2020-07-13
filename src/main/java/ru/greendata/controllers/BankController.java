package ru.greendata.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.greendata.dto.BankDto;
import ru.greendata.entity.Bank;
import ru.greendata.repository.BankRepository;
import ru.greendata.service.BankService;

@RestController
@RequestMapping("api/v1/bank")
public class BankController extends BaseController<BankDto, Bank, BankRepository>{

    public BankController(BankService service) {
        super(service);
    }
}
