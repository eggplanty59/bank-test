package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.greendata.repository"})
@ComponentScan(basePackages = {"ru.greendata.service"})
@EntityScan(basePackages = {"ru.greendata.entity"})
public class TestConfig {
}

