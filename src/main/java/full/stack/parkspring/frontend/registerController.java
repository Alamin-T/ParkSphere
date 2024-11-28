package full.stack.parkspring.frontend;


import com.fasterxml.jackson.databind.ObjectMapper;
import full.stack.parkspring.model.Gender;
import full.stack.parkspring.model.AppUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class
registerController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private TextField reEnterPasswordField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private Label invalidLoginMessage;

    @FXML
    private Button RegisterButton;

    @FXML
    private Polyline cancelButton;

    public void onPolylineHover() {
        cancelButton.setStyle("-fx-stroke: #000ea8; -fx-stroke-width: 4px;");
    }

    public void onPolylineExit() {
        cancelButton.setStyle("-fx-stroke: #8589f1; -fx-stroke-width: 2px;");
    }


    public void cancelButtonOnAction()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/login.fxml"));
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
    public void setInvalidRegisterMessageOnAction(ActionEvent event) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = enterPasswordField.getText();
            String confirmPassword = reEnterPasswordField.getText();
            Gender gender = maleRadioButton.isSelected() ? Gender.MALE : Gender.FEMALE;

            // Validate fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {
                invalidLoginMessage.setText("Please fill in all fields.");
                return;
            }
            if (!password.equals(confirmPassword)) {
                invalidLoginMessage.setText("Passwords do not match.");
                return;
            }

            AppUser user = new AppUser();
            user.setEmail(email);
            user.setUsername(firstName + lastName); // Generating a simple username
            user.setPassword(password);
            user.setGender(gender);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8080/api/users/register", user, String.class);

            // Handle response
            if (response.getStatusCode().is2xxSuccessful()) {
                invalidLoginMessage.setText("Registration successful!");
            } else {
                invalidLoginMessage.setText("Registration failed: " + response.getBody());
            }
        } catch (Exception e) {
            invalidLoginMessage.setText("Error occurred during registration.");
            e.printStackTrace(); // Print stack trace to debug the issue
        }
    }



}
