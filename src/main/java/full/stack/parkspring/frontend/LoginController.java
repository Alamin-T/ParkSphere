package full.stack.parkspring.frontend;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private Button LoginButton;

    @FXML
    private void loadAfterLoginPage() {
        String enteredEmail = usernameTextField.getText().trim();
        String enteredPassword = passwordTextField.getText().trim();

        Map<String, String> users = forgotPasswordController.getUsers();


        if (users.containsKey(enteredEmail.toLowerCase()) && users.get(enteredEmail.toLowerCase()).equals(enteredPassword)) {
            try {
                // Load the FXML file for the new page
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLogin.fxml"));
                Parent root = fxmlLoader.load();

                // Get the current stage (window)
                Stage stage = (Stage) LoginButton.getScene().getWindow();

                // Set the scene to the new root (afterLogin.fxml)
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            invalidLoginMessage.setText("Invalid Login! Please try again.");
        }

    }

    @FXML
    private Label invalidLoginMessage;

    public void setInvalidLoginMessageOnAction(ActionEvent event) throws IOException {

        if (usernameTextField.getText().isBlank() && passwordTextField.getText().isBlank()) {
            invalidLoginMessage.setText("Please enter username and password.");
        } else if (usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
            invalidLoginMessage.setText("Invalid Login! Please try again.");
        }


    }

    @FXML
    private PasswordField passwordTextField;


    @FXML
    private Label registerButton;

    @FXML
    public void onLabelHoverReg() {
        registerButton.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitReg() {
        registerButton.setStyle("-fx-font-size: 15px; -fx-text-fill: #868cf2; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelClickReg() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label forgotPasswordButton;

    @FXML
    public void onLabelHoverForgot() {
        forgotPasswordButton.setStyle("-fx-font-size: 13.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitForgot() {
        forgotPasswordButton.setStyle("-fx-font-size: 13px; -fx-text-fill: #868cf2; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelClickForgot() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgotPassword.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) forgotPasswordButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField usernameTextField;


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
