package com.intelmodus.barebone.config;

class BuildableBareboneConfiguration implements BareboneConfiguration {

    private final String applicationPath;
    private final int portNumber;

    BuildableBareboneConfiguration(String applicationPath, int portNumber) {
        this.applicationPath = applicationPath;
        this.portNumber = portNumber;
    }

    @Override
    public String applicationPath() {
        return applicationPath;
    }

    @Override
    public int portNumber() {
        return portNumber;
    }
}
