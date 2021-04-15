package com.intelmodus.barebone.server.jetty;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.servlet.GuiceFilter;
import com.intelmodus.barebone.config.BareboneConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Jetty implements com.intelmodus.barebone.server.Server {

    private static final String CONTEXT_ROOT = "/";

    private final BareboneConfiguration configuration;
    private final GuiceFilter filter;
    private final EventListenerScanner eventListenerScanner;
    private final HandlerScanner handlerScanner;

    @Inject
    Jetty(@Assisted BareboneConfiguration configuration, GuiceFilter filter, EventListenerScanner eventListenerScanner, HandlerScanner handlerScanner) {
        this.configuration = configuration;
        this.filter = filter;
        this.eventListenerScanner = eventListenerScanner;
        this.handlerScanner = handlerScanner;
    }

    public void start() {
        try {
            var server = new Server(configuration.portNumber());
            var filterHolder = new FilterHolder(filter);

            var context = new ServletContextHandler(server, CONTEXT_ROOT);
            context.addFilter(filterHolder, configuration.applicationPath() + "/*", NonSpecifiedDispatcherType());

            var servlet = new ServletHolder(new DefaultServlet());
            context.addServlet(servlet, CONTEXT_ROOT);

            eventListenerScanner.accept(context::addEventListener);

            var handlers = new HandlerCollection();
            handlers.addHandler(server.getHandler());
            handlerScanner.accept(handlers::addHandler);
            server.setHandler(handlers);
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EnumSet<DispatcherType> NonSpecifiedDispatcherType() {
        return EnumSet.noneOf(DispatcherType.class);
    }
}