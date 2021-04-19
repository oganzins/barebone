package com.intelmodus.barebone.rest;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import com.intelmodus.barebone.config.BareboneConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RestModuleTest {

    private static final String APPLICATION_PATH = "/api";
    @Mock
    private BareboneConfiguration configuration;

    @BeforeEach
    public void setUp() {
        given(configuration.applicationPath()).willReturn(APPLICATION_PATH);
    }

    @Test
    public void createGuiceFilter() {
        var injector = Guice.createInjector(new RestModule(configuration));
        var filter = injector.getInstance(GuiceFilter.class);

        assertThat(filter).isNotNull();
    }

}