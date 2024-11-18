package full.stack.parkspring.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class afterLoginController {

    @FXML
    private Button logoutButton;

    public void userLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene signInScene = new Scene(fxmlLoader.load(), 900, 550);

        Stage stage = Main.getStage();
        stage.setScene(signInScene);
        stage.show();
    }


}
