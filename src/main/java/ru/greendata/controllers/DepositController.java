package ru.greendata.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.greendata.dto.DepositDto;
import ru.greendata.entity.Deposit;
import ru.greendata.repository.DepositRepository;
import ru.greendata.service.DepositService;

@RestController
@RequestMapping("api/v1/deposit")
public class DepositController extends BaseController<DepositDto, Deposit, DepositRepository>{
    public DepositController(DepositService service) {
        super(service);
    }
}
