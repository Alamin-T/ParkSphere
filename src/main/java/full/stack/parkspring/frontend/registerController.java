package full.stack.parkspring.frontend;


import com.fasterxml.jackson.databind.ObjectMapper;
import full.stack.parkspring.model.Gender;
import full.stack.parkspring.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class registerController {

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

            User user = User.builder()
                    .email(email)
                    .username(firstName)
                    .password(password)
                    .gender(gender)
                    .build();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8081/api/users/register", user, String.class);

            // Handle response
            if (response.getStatusCode().is2xxSuccessful()) {
                invalidLoginMessage.setText("Registration successful!");
            } else {
                invalidLoginMessage.setText("Registration failed. Try again.");
            }
        } catch (Exception e) {
            invalidLoginMessage.setText("Error occurred during registration.");
            e.printStackTrace();
        }
    }
}
