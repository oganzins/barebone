package com.intelmodus.barebone.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ScannerTest {

    @Mock
    private Visitor<ScanningTarget> scanningTargetVisitor;
    @Mock
    private Visitor<NonBoundClass> nonBoundClassVisitor;

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new ModuleToBeScanned());
    }

    @Test
    public void appliesVisitor() {
        var scanner = new Scanner<>(injector, ScanningTarget.class);
        scanner.accept(scanningTargetVisitor);

        then(scanningTargetVisitor).should(times(2)).visit(any());
        then(scanningTargetVisitor).should().visit(any(DefaultScanningTarget.class));
        then(scanningTargetVisitor).should().visit(any(AlternateScanningTarget.class));
    }

    @Test
    public void noVisitorIsApplied() {
        var scanner = new Scanner<>(injector, NonBoundClass.class);
        scanner.accept(nonBoundClassVisitor);

        then(scanningTargetVisitor).should(never()).visit(any());
    }

}