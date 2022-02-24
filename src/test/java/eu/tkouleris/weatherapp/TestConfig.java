package eu.tkouleris.weatherapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class TestConfig {
    @Configuration
    @PropertySource("classpath:../application-test.properties")
    static class test{}
}
