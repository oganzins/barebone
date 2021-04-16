package com.intelmodus.barebone.reflect;

public class DefaultConstructorNotAvailable extends RuntimeException {

    DefaultConstructorNotAvailable(Exception exception) {
        super(exception);
    }

}
