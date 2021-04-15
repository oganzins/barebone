package com.intelmodus.barebone.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultBareboneConfigurationTest {

    private static final String EMPTY_APPLICATION_PATH = "";
    private static final int DEFAULT_PORT_NUMBER = 8080;

    @Test
    public void containsDefaultValues() {
        var configuration = new DefaultBareboneConfiguration();

        assertThat(configuration)
                .returns(EMPTY_APPLICATION_PATH, BareboneConfiguration::applicationPath)
                .returns(DEFAULT_PORT_NUMBER, BareboneConfiguration::portNumber);
    }

}