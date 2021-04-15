package com.intelmodus.barebone.server.jetty;

import javax.inject.Inject;

import com.intelmodus.barebone.inject.Scanner;
import org.eclipse.jetty.server.Handler;

import com.google.inject.Injector;

class HandlerScanner extends Scanner<Handler> {

    @Inject
    HandlerScanner(Injector injector) {
        super(injector, Handler.class);
    }
}