package com.intelmodus.barebone.reflect;

import java.lang.reflect.Constructor;

public class Instance<T> {

    private Class<T> clazz;

    private Instance(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> Instance<T> of(Class<T> clazz) {
        return new Instance<>(clazz);
    }

    public T byDefaultConstructor() {
        try {
            return defaultConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<T> defaultConstructor() throws NoSuchMethodException {
        return clazz.getDeclaredConstructor();
    }

}
