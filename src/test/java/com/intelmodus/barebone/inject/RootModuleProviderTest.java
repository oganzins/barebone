package com.intelmodus.barebone.inject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RootModuleProviderTest {

    @Test
    public void providesRootModule() {
        var rootModule = RootModuleProvider.of(DummyApplication.class).get();
        assertThat(rootModule).isInstanceOf(DummyModule.class);
    }

    @Test
    public void throwsMissingRootModuleAnnotation() {
        var rootModuleProvider = RootModuleProvider.of(DummyApplicationWithoutModule.class);

        assertThatThrownBy(rootModuleProvider::get).isInstanceOf(MissingRootModuleAnnotation.class)
                .hasMessage("Root module annotation is missing for class com.intelmodus.barebone.inject.DummyApplicationWithoutModule!");
    }

}