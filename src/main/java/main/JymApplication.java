package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@SpringBootApplication
@ComponentScan(basePackages = "main")
public class JymApplication  {
    private static final Logger logger = LoggerFactory.getLogger(JymApplication .class);

    public static void main(String[] args) {
        SpringApplication.run(JymApplication .class, args);
        logger.info("LibraryApplication has started successfully!");
    }
}
