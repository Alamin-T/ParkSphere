package full.stack.parkspring.frontend;

import full.stack.parkspring.config.ApplicationContextProvider;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    @FXML
    private Button LoginButton;

    @FXML
    private Label invalidLoginMessage;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label registerButton;

    @FXML
    private Label forgotPasswordButton;

    @FXML
    private TextField usernameTextField;

    public LoginController() {
        this.userRepository = ApplicationContextProvider.getContext().getBean(UserRepository.class);
    }

    // Method to load the after-login page
    @FXML
    private void loadAfterLoginPage() {
        String enteredEmail = usernameTextField.getText().trim();
        String enteredPassword = passwordTextField.getText().trim();

        // Fetch user from the database
        Optional<AppUser> optionalUser = userRepository.findByEmail(enteredEmail.toLowerCase());

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(enteredPassword)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/afterLogin.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) LoginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            invalidLoginMessage.setText("Invalid Login! Please try again.");
        }
    }

    // Method to set invalid login message based on user input
    public void setInvalidLoginMessageOnAction() {
        if (usernameTextField.getText().isBlank() && passwordTextField.getText().isBlank()) {
            invalidLoginMessage.setText("Please enter username and password.");
        } else if (usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
            invalidLoginMessage.setText("Invalid Login! Please try again.");
        }
    }

    // Methods for handling hover and click events for the "Register" label
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/register.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods for handling hover and click events for the "Forgot Password" label
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/forgotPassword.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) forgotPasswordButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show an alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
