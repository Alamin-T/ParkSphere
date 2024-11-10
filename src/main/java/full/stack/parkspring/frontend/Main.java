package full.stack.parkspring.frontend;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350 , 760);
        stage.setTitle("Park Sphere");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stg;
    }



    public static void main(String[] args) {
        launch();
    }
}