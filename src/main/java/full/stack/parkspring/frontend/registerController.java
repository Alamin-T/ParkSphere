package full.stack.parkspring.frontend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class registerController {

    @FXML
    private TextField firstNameField;


    @FXML
    private Button RegisterButton;

    @FXML
    private DatePicker dateOfBirthField;

    @FXML
    private TextField emailField;

    @FXML
    private RadioButton femaleRadioButton;



    @FXML
    private Label invalidLoginMessage;

    @FXML
    private TextField lastNameField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton otherRadioButton;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private TextField reEnterPasswordField;

    @FXML
    public void setCancelMessageOnAction(ActionEvent event) {
        // Logic to handle cancel action
        // For example, you could clear the fields or display a message
        invalidLoginMessage.setText("Registration cancelled.");
    }

    @FXML
    public void setInvalidRegisterMessageOnAction() {
        // Example validation logic
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || enterPasswordField.getText().isEmpty() ||
                reEnterPasswordField.getText().isEmpty()) {
            invalidLoginMessage.setText("Please fill in all fields.");
        } else if (!enterPasswordField.getText().equals(reEnterPasswordField.getText())) {
            invalidLoginMessage.setText("Passwords do not match.");
        } else {
            // Registration logic goes here (e.g., save to database)
            invalidLoginMessage.setText("Registration successful!");
        }
    }


    @FXML
    private Polyline cancelButton;
    @FXML
    public void onPolylineHover() {
        cancelButton.setStyle("-fx-stroke: #000ea8; -fx-stroke-width: 4px;");
    }

    @FXML
    public void onPolylineExit() {
        cancelButton.setStyle("-fx-stroke: #8589f1; -fx-stroke-width: 2px;");
    }


    public void cancelButtonOnAction() throws IOException {
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


}