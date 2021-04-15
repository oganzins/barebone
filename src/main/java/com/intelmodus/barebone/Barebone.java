package com.intelmodus.barebone;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.intelmodus.barebone.config.BareboneConfiguration;
import com.intelmodus.barebone.config.BareboneConfigurationBuilder;
import com.intelmodus.barebone.config.DefaultBareboneConfiguration;
import com.intelmodus.barebone.inject.RootModuleProvider;
import com.intelmodus.barebone.rest.RestModule;
import com.intelmodus.barebone.server.ServerFactory;
import com.intelmodus.barebone.server.ServerModule;


import java.util.List;

public class Barebone {

    private final BareboneConfiguration configuration;

    public Barebone(BareboneConfiguration configuration) {
        this.configuration = configuration;
    }

    public static void boot(Class<?> applicationClass) {
        boot(applicationClass, new DefaultBareboneConfiguration());
    }

    public static void boot(Class<?> applicationClass, BareboneConfiguration configuration) {
        var barebone = new Barebone(configuration);
        barebone.run(applicationClass);
    }

    public static BareboneConfigurationBuilder configurationBuilder() {
        return new BareboneConfigurationBuilder();
    }

    private void run(Class<?> applicationClass) {
        var serverFactory = injectorOf(applicationClass).getInstance(ServerFactory.class);
        var server = serverFactory.create(configuration);
        server.start();
    }

    private Injector injectorOf(Class<?> applicationClass) {
        return Guice.createInjector(modulesOf(applicationClass));
    }

    private List<Module> modulesOf(Class<?> applicationClass) {
        return ImmutableList.of(new ServerModule(),
                new RestModule(configuration),
                rootModuleOf(applicationClass));
    }

    private Module rootModuleOf(Class<?> applicationClass) {
        return RootModuleProvider.of(applicationClass).get();
    }

}
