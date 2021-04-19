package com.intelmodus.barebone.server;

public class ServerStartupFailed extends RuntimeException {

    public ServerStartupFailed(Exception exception) {
        super(exception);
    }

}
