package com.example.hometask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

// Показывает все зарегистрированные URL при старте
@Component
public class EndpointLogger implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public EndpointLogger(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        mapping.getHandlerMethods().forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
