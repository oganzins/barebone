package com.intelmodus.barebone.rest;

import com.google.inject.AbstractModule;
import com.intelmodus.barebone.config.BareboneConfiguration;
import com.intelmodus.barebone.rest.resteasy.RestEasyModule;

public class RestModule extends AbstractModule {

    private BareboneConfiguration serverConfiguration;

    public RestModule(BareboneConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    protected void configure() {
        install(new RestEasyModule(serverConfiguration.applicationPath()));
    }

}
