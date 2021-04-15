package com.intelmodus.barebone.server.jetty;

import java.util.EventListener;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.intelmodus.barebone.inject.Scanner;

class EventListenerScanner extends Scanner<EventListener> {

    @Inject
    EventListenerScanner(Injector injector) {
        super(injector, EventListener.class);
    }
}