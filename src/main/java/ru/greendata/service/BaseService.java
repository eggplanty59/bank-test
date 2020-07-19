package ru.greendata.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.greendata.dto.BaseDto;
import ru.greendata.dto.params.*;
import ru.greendata.entity.BaseEntity;
import ru.greendata.entity.filters.BaseSpecification;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BaseService<Entity extends BaseEntity<Dto>, Dto extends BaseDto<Entity>, Repository extends JpaRepository<Entity, Integer> & JpaSpecificationExecutor<Entity>>  {
    protected final Repository repository;

    public BaseService(Repository repository) {
        this.repository = repository;
    }

    public BaseService() {
        this.repository = null;
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
        if(params == null)
            params = new Params();
        List<Entity> list;
        if(params.getFilterCriteria() != null && params.getOrders() != null)
            list = repository.findAll(getEntitySelect(params.getFilterCriteria(), null), getOrders(params));
        else if(params.getFilterCriteria() == null && params.getOrders() != null)
            list = repository.findAll(getOrders(params));
        else if(params.getFilterCriteria() != null && params.getOrders() == null)
            list = repository.findAll(getEntitySelect(params.getFilterCriteria(),null));
        else
            list =repository.findAll();

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

    private Specification<Entity> getEntitySelect(FilterParams filterParams, Specification<Entity> spec){
        if (filterParams.getFilterParamsList() != null)
        for (FilterParams insertedFilterParams: filterParams.getFilterParamsList()) {


            Specification<Entity> specification = getEntitySelect( insertedFilterParams, spec);
            if (filterParams.getOperation().equalsIgnoreCase("or"))
                spec = spec == null ? Specification.where(specification) : spec.or(specification);
            else
                spec = spec == null ? Specification.where(specification) : spec.and(specification);
        }
        if (filterParams.getFilterCriteriaList() != null)
        for (FilterCriteria filterCriteria: filterParams.getFilterCriteriaList()) {
            BaseSpecification baseSpecification = new BaseSpecification(filterCriteria);
            //если ничего нет или любая надпись, то считаем что будем делать "и"
            if (filterParams.getOperation().equalsIgnoreCase("or"))
                spec = spec == null ? Specification.where(baseSpecification) : spec.or(baseSpecification);
            else
                spec = spec == null ? Specification.where(baseSpecification) : spec.and(baseSpecification);
        }
        return spec;
    }

    private Sort getOrders(Params params){
        Sort sort = null;
        if(params.getOrders() == null)
            return null;
        for(OrderParams orderParams: params.getOrders()) {
            boolean asc = orderParams.getOrderDir() != null && orderParams.getOrderDir().equalsIgnoreCase("asc");
            Sort.Direction sortDir = asc ? Sort.Direction.ASC : Sort.Direction.DESC;

            if (orderParams.getOrderBy() == null) {
                return Sort.by(sortDir, "id");
            }

            sort = sort == null ? (Sort.by(sortDir, orderParams.getOrderBy())) : sort.and(Sort.by(sortDir, orderParams.getOrderBy()));

        }
        return sort;

    }

    public void delete(Object id) {
    }
}
