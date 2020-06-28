package org.tec.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// configs need to be here and not on the spring boot annotation...
@ComponentScan(basePackages ={"org.tec.lambda.config", "org.tec.lambda.svc"})
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
