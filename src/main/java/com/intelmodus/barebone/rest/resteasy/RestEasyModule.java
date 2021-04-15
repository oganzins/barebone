package com.intelmodus.barebone.rest.resteasy;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class RestEasyModule extends ServletModule {

    private static final String APPLICATION_PATH_PARAMETER = "resteasy.servlet.mapping.prefix";

    private final String applicationPath;

    public RestEasyModule(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    @Override
    protected void configureServlets() {
        bindClasses();
        configureApplicationServlet();
    }

    private void bindClasses() {
        bind(GuiceResteasyBootstrapServletContextListener.class);
        bind(HttpServletDispatcher.class).in(Singleton.class);
    }

    private void configureApplicationServlet() {
        serve(applicationPath + "/*").with(HttpServletDispatcher.class, servletParametersOf(applicationPath));
    }

    private ImmutableMap<String, String> servletParametersOf(String path) {
        return ImmutableMap.of(APPLICATION_PATH_PARAMETER, path);
    }

}