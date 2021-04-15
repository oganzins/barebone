package com.intelmodus.barebone.config;

public class DefaultBareboneConfiguration implements BareboneConfiguration {

    @Override
    public String applicationPath() {
        return "";
    }

    @Override
    public int portNumber() {
        return 8080;
    }

}
