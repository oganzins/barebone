package com.intelmodus.barebone.server;

import com.google.inject.assistedinject.Assisted;
import com.intelmodus.barebone.config.BareboneConfiguration;

public interface ServerFactory {

    Server create(@Assisted BareboneConfiguration configuration);

}
