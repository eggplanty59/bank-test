package ru.greendata.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.greendata.dto.BaseDto;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.BaseEntity;
import ru.greendata.service.BaseService;

import java.util.List;

public class BaseController<Dto extends BaseDto<Entity>, Entity extends BaseEntity<Dto>, Repo extends JpaRepository<Entity,Integer> & JpaSpecificationExecutor<Entity>> {
    private final BaseService<Entity, Dto , Repo> service;

    public BaseController(BaseService service) {
        this.service = service;
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Dto> getList(@RequestBody Params params) {
        return service.list(params);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dto create(@RequestBody Dto dto) {
        return service.create(dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Dto update(@RequestBody Dto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
