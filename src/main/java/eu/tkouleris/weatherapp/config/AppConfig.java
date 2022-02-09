package eu.tkouleris.weatherapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class AppConfig {
    @Configuration
    @PropertySource("application-local.properties")
    static class local{}
}
