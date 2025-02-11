package main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsLogger implements CommandLineRunner {
    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointsLogger(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void run(String... args) {
        handlerMapping.getHandlerMethods().forEach((key, value) ->
                System.out.println(key + " -> " + value));
    }
}
