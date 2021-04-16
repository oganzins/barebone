package com.intelmodus.barebone.inject;

import com.google.inject.AbstractModule;

class ModuleToBeScanned extends AbstractModule {

    @Override
    protected void configure() {
        bind(DefaultScanningTarget.class);
        bind(AlternateScanningTarget.class);
        bind(ByPassedTarget.class);
    }
}
