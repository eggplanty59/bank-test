package ru.greendata.dto;

public abstract class BaseDto<Entity> {

    public abstract  Integer getId();

    public abstract Entity toEntity();
}
