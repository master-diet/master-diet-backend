package pl.agh.edu.master_diet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.agh.edu.master_diet.config.log.RequestAndResponseLoggingFilter;

@Configuration
class FilterConfiguration {

    @Bean
    public RequestAndResponseLoggingFilter get() {
        return new RequestAndResponseLoggingFilter();
    }
}
