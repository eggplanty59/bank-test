package ru.greendata.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.greendata.dto.params.BankParams;
import ru.greendata.dto.params.Page;
import ru.greendata.dto.params.PageParams;
import ru.greendata.entity.Bank;
import ru.greendata.service.BankService;

import java.util.List;

@RestController
@RequestMapping("api/v1/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<Bank> findAll(){
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public Bank findById(@PathVariable String id){
        return bankService.findById(id);
    }

    @PostMapping("/list")
    public Page<Bank> getList(@RequestBody PageParams<BankParams> pageParams) {
        return bankService.list(pageParams);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bank create(@RequestBody Bank bank){
        return bankService.create(bank);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Bank update(@RequestBody Bank bank){
        return bankService.update(bank);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        bankService.delete(id);
    }
}
