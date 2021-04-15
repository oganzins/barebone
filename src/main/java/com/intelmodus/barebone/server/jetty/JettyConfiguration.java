package com.intelmodus.barebone.server.jetty;

public class JettyConfiguration {

    private final String applicationPath;
    private final Integer portNumber;

    public JettyConfiguration(String applicationPath, Integer portNumber) {
        this.applicationPath = applicationPath;
        this.portNumber = portNumber;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public Integer getPortNumber() {
        return portNumber;
    }
}
