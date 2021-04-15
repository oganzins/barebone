package com.intelmodus.barebone.inject;

import com.google.inject.Module;
import com.intelmodus.barebone.RootModule;
import com.intelmodus.barebone.reflect.Instance;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class RootModuleProvider {

    private Class<?> applicationClass;

    private RootModuleProvider(Class<?> applicationClass) {
        this.applicationClass = applicationClass;
    }

    public static RootModuleProvider of(Class<?> applicationClass) {
        return new RootModuleProvider(applicationClass);
    }

    public Module get() {
        return rootModule().orElseThrow(missingRootModuleAnnotation());
    }

    private Supplier<MissingRootModuleAnnotation> missingRootModuleAnnotation() {
        return () -> new MissingRootModuleAnnotation(applicationClass);
    }

    private Optional<Module> rootModule() {
        return Arrays.stream(rootModuleAnnotationsOf(applicationClass))
                .map(toModuleClass())
                .map(toInstance())
                .findFirst();
    }

    private RootModule[] rootModuleAnnotationsOf(Class<?> applicationClass) {
        return applicationClass.getAnnotationsByType(RootModule.class);
    }

    private Function<RootModule, Class<? extends Module>> toModuleClass() {
        return RootModule::value;
    }

    private Function<Class<? extends Module>, Module> toInstance() {
        return moduleClass -> Instance.of(moduleClass).byDefaultConstructor();
    }

}
