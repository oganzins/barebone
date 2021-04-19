package com.intelmodus.barebone.rest.resteasy;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestEasyModuleTest {

    private static final String APPLICATION_PATH = "/my/first/application";

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new RestEasyModule(APPLICATION_PATH));
    }

    @Test
    public void createsGuiceServerContextListener() {
        var listener = createGuiceServerContextListener();

        assertThat(listener)
                .isNotNull()
                .isNotSameAs(createGuiceServerContextListener());
    }

    @Test
    public void createsHttpServletDispatcher() {
        var dispatcher = createHttpServletDispatcher();

        assertThat(dispatcher)
                .isNotNull()
                .isSameAs(createHttpServletDispatcher());
    }

    private GuiceResteasyBootstrapServletContextListener createGuiceServerContextListener() {
        return injector.getInstance(GuiceResteasyBootstrapServletContextListener.class);
    }

    private HttpServletDispatcher createHttpServletDispatcher() {
        return injector.getInstance(HttpServletDispatcher.class);
    }

}