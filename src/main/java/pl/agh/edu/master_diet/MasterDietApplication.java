package pl.agh.edu.master_diet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.agh.edu.master_diet.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MasterDietApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterDietApplication.class, args);
    }
}
