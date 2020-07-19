package ru.greendata.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.BaseDto;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.BaseEntity;

import java.util.Arrays;
import java.util.List;

public interface IService<Entity extends BaseEntity<Dto>, Dto extends BaseDto<Entity>, Repository extends JpaRepository<Entity, Integer> & JpaSpecificationExecutor<Entity>> {

    public Dto create(Dto bank);

    public Dto update(Dto bank);

    public void delete(Object id);

    public List<Dto> list(Params params);
}
