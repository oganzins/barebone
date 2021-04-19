package com.intelmodus.barebone.server.jetty;

import com.google.inject.assistedinject.Assisted;
import com.intelmodus.barebone.config.BareboneConfiguration;
import com.intelmodus.barebone.server.ServerStartupFailed;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;

import javax.inject.Inject;

public class Jetty implements com.intelmodus.barebone.server.Server {

    private final BareboneConfiguration configuration;
    private final EventListenerScanner eventListenerScanner;
    private final HandlerScanner handlerScanner;
    private final JettyServerFactory jettyServerFactory;
    private ServletContextFactory servletContextFactory;

    @Inject
    Jetty(@Assisted BareboneConfiguration configuration,
          EventListenerScanner eventListenerScanner,
          HandlerScanner handlerScanner,
          JettyServerFactory jettyServerFactory,
          ServletContextFactory servletContextFactory) {
        this.configuration = configuration;
        this.eventListenerScanner = eventListenerScanner;
        this.handlerScanner = handlerScanner;
        this.jettyServerFactory = jettyServerFactory;
        this.servletContextFactory = servletContextFactory;
    }

    public void start() {
        try {
            var server = createServer();
            server.start();
            server.join();
        } catch (Exception e) {
            throw new ServerStartupFailed(e);
        }
    }

    private Server createServer() {
        var server = jettyServerFactory.create(configuration);
        var context = servletContextFactory.create(server, configuration);
        eventListenerScanner.accept(context::addEventListener);
        var handlers = handlersOf(server);
        handlerScanner.accept(handlers::addHandler);
        server.setHandler(handlers);
        return server;
    }

    private HandlerCollection handlersOf(Server server) {
        var handlers = new HandlerCollection();
        handlers.addHandler(server.getHandler());
        return handlers;
    }
}