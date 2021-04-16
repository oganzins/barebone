package com.intelmodus.barebone.reflect;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InstanceTest {

    @Test
    public void instantiatesClassUsingDefaultConstructor() {
        var dummy = Instance.of(Dummy.class).byDefaultConstructor();

        assertThat(dummy)
                .isInstanceOf(Dummy.class);
    }

    @Test
    public void throwsRuntimeExceptionWhenNoDefaultConstructorIsPresent() {
        var instance = Instance.of(DummyWithoutDefaultConstructor.class);

        assertThatThrownBy(instance::byDefaultConstructor)
                .isInstanceOf(DefaultConstructorNotAvailable.class);
    }

}