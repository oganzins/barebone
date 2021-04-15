package com.intelmodus.barebone.inject;

public class MissingRootModuleAnnotation extends RuntimeException {

    MissingRootModuleAnnotation(Class<?> clazz) {
        super(errorMessageOf(clazz));
    }

    private static String errorMessageOf(Class<?> clazz) {
        return "Root module annotation is missing for " + clazz.toString() + "!";
    }

}
