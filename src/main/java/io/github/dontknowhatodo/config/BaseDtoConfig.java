package io.github.dontknowhatodo.config;

public abstract class BaseDtoConfig<E> {
    public abstract void loadFromEntity(E entity);
    public abstract E toEntity();
}