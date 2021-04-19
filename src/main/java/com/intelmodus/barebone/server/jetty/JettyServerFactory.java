package com.intelmodus.barebone.server.jetty;

import com.google.inject.Singleton;
import com.intelmodus.barebone.config.BareboneConfiguration;
import org.eclipse.jetty.server.Server;

@Singleton
class JettyServerFactory {

    Server create(BareboneConfiguration configuration) {
        return new Server(configuration.portNumber());
    }

}
