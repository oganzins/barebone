package com.intelmodus.barebone.config;

public class BareboneConfigurationBuilder {

    private static final int DEFAULT_PORT_NUMBER = 8080;
    private static final String EMPTY_APPLICATION_PATH = "";

    private String applicationPath;
    private int portNumber;

    public BareboneConfigurationBuilder() {
        this.portNumber = DEFAULT_PORT_NUMBER;
        this.applicationPath = EMPTY_APPLICATION_PATH;
    }

    public BareboneConfigurationBuilder applicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
        return this;
    }

    public BareboneConfigurationBuilder portNumber(int portNumber) {
        this.portNumber = portNumber;
        return this;
    }

    public BareboneConfiguration build() {
        return new BuildableBareboneConfiguration(applicationPath, portNumber);
    }

}
