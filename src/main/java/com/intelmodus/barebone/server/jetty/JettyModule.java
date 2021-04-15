package com.intelmodus.barebone.server.jetty;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.servlet.GuiceFilter;
import com.intelmodus.barebone.server.Server;
import com.intelmodus.barebone.server.ServerFactory;

public class JettyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventListenerScanner.class);
        bind(HandlerScanner.class);
        bind(GuiceFilter.class);

        install(ServerFactory());
    }

    private Module ServerFactory() {
        return new FactoryModuleBuilder().implement(Server.class, Jetty.class).build(ServerFactory.class);
    }
}
