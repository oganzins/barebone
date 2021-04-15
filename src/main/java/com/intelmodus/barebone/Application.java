package com.intelmodus.barebone;

import com.intelmodus.barebone.config.BareboneConfiguration;
import com.intelmodus.barebone.resource.ResourceModule;

@RootModule(ResourceModule.class)
public class Application {

    public static void main(String[] args) {
        Barebone.boot(Application.class, configuration());
    }

    private static BareboneConfiguration configuration() {
        return Barebone.configurationBuilder()
                .applicationPath("/api")
                .portNumber(8080)
                .build();
    }

}
