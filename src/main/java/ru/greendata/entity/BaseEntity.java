package ru.greendata.entity;

public abstract class BaseEntity<Dto> {
    public static String TYPE_NAME = "BaseEntity";

    public abstract Dto toDto();
}
