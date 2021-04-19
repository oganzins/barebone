package com.intelmodus.barebone.rest.resteasy;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.util.Modules;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ApplicationPathTest {

    private static final String APPLICATION_PATH = "/my/first/application";

    @Mock(answer = Answers.RETURNS_MOCKS)
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private FilterConfig filterConfig;
    @Mock
    private ServletContext servletContext;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private HttpServletDispatcher mockedHttpServletDispatcher;

    private Injector injector;


    @BeforeEach
    public void setUp() {
        var module = Modules.override(new RestEasyModule(APPLICATION_PATH)).with(mockedHttpServletDispatcherModule());
        injector = Guice.createInjector(module);
    }


    @Test
    public void servesRequest() throws IOException, ServletException {
        given(filterConfig.getServletContext()).willReturn(servletContext);
        given(request.getRequestURI()).willReturn("/my/first/application/orders");

        serve(request, response);

        then(mockedHttpServletDispatcher).should().service(any(), eq(response));
    }


    @Test
    public void requestIsNotServed() throws IOException, ServletException {
        given(filterConfig.getServletContext()).willReturn(servletContext);
        given(request.getRequestURI()).willReturn("/api/orders");

        serve(request, response);

        then(mockedHttpServletDispatcher).should(never()).service(any(), any());
    }

    private AbstractModule mockedHttpServletDispatcherModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bind(HttpServletDispatcher.class).toInstance(mockedHttpServletDispatcher);
            }
        };
    }


    private void serve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        filter().doFilter(request, response, filterChain);
    }

    private GuiceFilter filter() throws ServletException {
        var filter = injector.getInstance(GuiceFilter.class);
        filter.init(filterConfig);
        return filter;
    }

}