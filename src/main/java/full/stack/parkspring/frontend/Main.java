package full.stack.parkspring.frontend;

import full.stack.parkspring.Manager;
import full.stack.parkspring.ParkSpringApplication;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

    private static ApplicationContext springContext;

    private static Stage stg;
    static Manager manager;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/controller_fxml/homePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350 , 760);
        stage.setTitle("Park Sphere");
        stage.setScene(scene);
        stage.show();
    }

   /* @Override
    public void stop() throws Exception {
        manager.stop();
        super.stop();
    } */

    public static Stage getStage() {
        return stg;
    }



    public static void main(String[] args) {
        springContext = new SpringApplicationBuilder(ParkSpringApplication.class).run(args);

        launch(args);
    }
}