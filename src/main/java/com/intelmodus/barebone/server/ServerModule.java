package com.intelmodus.barebone.server;

import com.google.inject.AbstractModule;
import com.intelmodus.barebone.server.jetty.JettyModule;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new JettyModule());
    }

}
