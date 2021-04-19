package com.intelmodus.barebone.server.jetty;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.intelmodus.barebone.config.BareboneConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Singleton
class ServletContextFactory {

    private static final String CONTEXT_ROOT = "/";

    private GuiceFilter filter;

    @Inject
    ServletContextFactory(GuiceFilter filter) {
        this.filter = filter;
    }

    ServletContextHandler create(Server server, BareboneConfiguration configuration) {
        var context = new ServletContextHandler(server, CONTEXT_ROOT);
        var filterHolder = new FilterHolder(filter);
        context.addFilter(filterHolder, configuration.applicationPath() + "/*", NonSpecifiedDispatcherType());
        var servlet = new ServletHolder(new DefaultServlet());
        context.addServlet(servlet, CONTEXT_ROOT);
        return context;
    }

    private EnumSet<DispatcherType> NonSpecifiedDispatcherType() {
        return EnumSet.noneOf(DispatcherType.class);
    }

}
