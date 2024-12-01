package full.stack.parkspring;

import full.stack.parkspring.frontend.Main;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan(basePackages = "full.stack.parkspring")
public class ParkSpringApplication {

    public static void main(String[] args) {
        // Run Spring Boot application
        SpringApplication.run(ParkSpringApplication.class, args);

        // Launch JavaFX application
        Application.launch(Main.class, args);
    }
}
