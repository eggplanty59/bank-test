package ru.greendata.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.greendata.dto.BaseDto;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.BaseEntity;
import ru.greendata.entity.filters.BaseSpecification;
import ru.greendata.entity.filters.FilterCriteria;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BaseService<Entity extends BaseEntity<Dto>, Dto extends BaseDto<Entity>, Repository extends JpaRepository<Entity, Integer> & JpaSpecificationExecutor<Entity>>  {
    protected final Repository repository;

    public BaseService(Repository repository) {
        this.repository = repository;
    }

    public Dto create(Dto dto){
        return repository.save(dto.toEntity()).toDto();
    }

    public Dto update(Dto dto) {
        if(dto == null) {
            throw new EntityIllegalArgumentException("Изменяемый объект не может быть null");
        }
        if(dto.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Entity existedEntity = repository.findById(dto.getId())
                .orElseThrow(()->new EntityNotFoundException(Entity.TYPE_NAME, dto.getId()));
        return repository.save(dto.toEntity()).toDto();
    }

    public List<Dto> list(Params params) {

        List<Entity> list = repository.findAll(getEntitySelect(params), getOrderBy(params));
        List<Dto> listDto = new ArrayList<>();
        list.stream().forEach((entity -> listDto.add(entity.toDto())));
        return listDto;
    }

    public Entity findById(Object id){
        Entity entity;
        if(id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Integer parsedId;
        try {
            parsedId = Integer.valueOf(id.toString());
        }catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к нужному типу, текст ошибки: %s", ex));
        }
        entity = repository.findById(parsedId)
                .orElseThrow(()->new EntityNotFoundException(Entity.TYPE_NAME, parsedId));
        return entity;
    }

    private Specification<Entity> getEntitySelect(Params params){
        Specification spec = null;
        List<FilterCriteria> listFilterCriteria = params.getFilterCriteria();
        if (listFilterCriteria == null){
            return null;
        }
        for (FilterCriteria filterCriteria: listFilterCriteria){
            BaseSpecification baseSpecification = new BaseSpecification(filterCriteria);
            spec = spec == null ? Specification.where(baseSpecification) : spec.and(baseSpecification);
        }
        return spec;
    }

    private Sort getOrderBy(Params params){
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

    public void delete(Object id) {
    }
}
