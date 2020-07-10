package ru.greendata.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.greendata.entity.Deposit;
import ru.greendata.service.DepositService;

import java.util.List;

@RestController
@RequestMapping("api/v1/deposit")
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public List<Deposit> findAll(){
        return depositService.findAll();
    }

    @GetMapping("/{id}")
    public Deposit findById(@PathVariable String id){
        return depositService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Deposit create(@RequestBody Deposit deposit){
        return depositService.create(deposit);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Deposit update(@RequestBody Deposit deposit){
        return depositService.update(deposit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        depositService.delete(id);
    }
}
