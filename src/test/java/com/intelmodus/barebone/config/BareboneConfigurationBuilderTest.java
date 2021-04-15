package com.intelmodus.barebone.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BareboneConfigurationBuilderTest {

    private static final String APPLICATION_PATH = "/api";
    private static final int PORT_NUMBER = 9090;

    private static final String EMPTY_APPLICATION_PATH = "";
    private static final int DEFAULT_PORT_NUMBER = 8080;

    @Test
    public void buildsConfiguration() {
        var configuration = new BareboneConfigurationBuilder()
                .applicationPath(APPLICATION_PATH)
                .portNumber(PORT_NUMBER)
                .build();

        assertThat(configuration)
                .returns(APPLICATION_PATH, BareboneConfiguration::applicationPath)
                .returns(PORT_NUMBER, BareboneConfiguration::portNumber);
    }

    @Test
    public void buildsConfigurationWithDefaultValues() {
        var configuration = new BareboneConfigurationBuilder().build();

        assertThat(configuration)
                .returns(EMPTY_APPLICATION_PATH, BareboneConfiguration::applicationPath)
                .returns(DEFAULT_PORT_NUMBER, BareboneConfiguration::portNumber);
    }

}