package full.stack.parkspring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Manager {
    private ConfigurableApplicationContext ctx;

    public void start() {
        ctx = SpringApplication.run(ParkSpringApplication.class);
    }

    public void stop() {
        ctx.close();
    }
}
