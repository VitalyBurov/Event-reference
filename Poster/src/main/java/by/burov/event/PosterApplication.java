package by.burov.event;

import by.burov.event.service.UserHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories("by.burov.event.repository")
public class PosterApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PosterApplication.class,args);
    }
}
