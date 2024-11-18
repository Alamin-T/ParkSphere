package full.stack.parkspring.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class forgotPasswordController {


    @FXML
    private Label cancelButton;

    @FXML
    public void onLabelHoverCancel() {
        cancelButton.setStyle("-fx-font-size: 13.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }
    @FXML
    public void onLabelExitCancel() {
        cancelButton.setStyle("-fx-font-size: 13px; -fx-text-fill: #868cf2; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }

    public void cancelButtonOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private TextField forgotPasswordUsernameField;

    @FXML
    private Button passwordResetButton;


    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("kaz", "kaz123");
        users.put("elsa", "elsa123");
        users.put("alamin","alamin123");
        users.put("bob","bob123");
        users.put("john","john123");
        users.put("jane","jane123");

    }

    public static Map<String, String> getUsers() {
        return users;
    }

    public void handleReset(){
        String email = forgotPasswordUsernameField.getText();

        if (users.containsKey(email)) {
            if (sendResetEmail(email)){
                users.put(email,"0000");
                showAlert(Alert.AlertType.INFORMATION,"Success","A reset email has been sent! Your password is now '0000'.");
            }
            else {
                showAlert(Alert.AlertType.ERROR,"Error","Failed to send email.");
            }
        }
        else {
            showAlert(Alert.AlertType.WARNING,"Warning","Email not found");
        }

    }

    private boolean sendResetEmail(String toEmail){
        try {
            System.out.println("Email sent to: " + toEmail);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}