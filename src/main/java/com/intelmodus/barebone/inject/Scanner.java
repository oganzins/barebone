package com.intelmodus.barebone.inject;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Scanner<T> {

    private final Injector injector;
    private final Class<T> scanFor;

    public Scanner(Injector injector, Class<T> scanFor) {
        this.injector = injector;
        this.scanFor = scanFor;
    }

    public void accept(Visitor<T> visitor) {
        accept(injector, visitor);
    }

    private void accept(Injector injector, Visitor<T> visitor) {
        entriesOf(injector).stream()
                .map(toBinding())
                .filter(matchesScanFor())
                .forEach(apply(visitor));
    }

    private Consumer<Binding<?>> apply(Visitor<T> visitor) {
        return binding -> visitor.visit(providerOf(binding));
    }

    @SuppressWarnings("unchecked")
    private T providerOf(Binding<?> binding) {
        return (T) binding.getProvider().get();
    }

    private Predicate<Binding<?>> matchesScanFor() {
        return binding -> {
            var type = typeOf(binding);
            return type instanceof Class && scanFor.isAssignableFrom((Class<?>) type);
        };
    }

    private Type typeOf(Binding<?> binding) {
        return binding.getKey().getTypeLiteral().getType();
    }

    private Set<Map.Entry<Key<?>, Binding<?>>> entriesOf(Injector injector) {
        return injector.getBindings().entrySet();
    }

    private Function<Map.Entry<Key<?>, Binding<?>>, Binding<?>> toBinding() {
        return Map.Entry::getValue;
    }

}