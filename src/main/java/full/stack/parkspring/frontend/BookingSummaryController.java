package full.stack.parkspring.frontend;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class BookingSummaryController {

    @FXML
    private Label carLabel;

    @FXML
    private Label parkingTypeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button reserveButton;

    @FXML
    private Label timeSlotLabel;


    // Setters for summary details
    public void setTimeSlot(String timeSlot) {
        timeSlotLabel.setText(timeSlot);
    }

    public void setSummaryDetails(String car, String parkingType, String date, String timeSlot) {
        carLabel.setText(car);
        parkingTypeLabel.setText(parkingType);
        dateLabel.setText(date);
        timeSlotLabel.setText(timeSlot);

    }

    // Handle confirm button click
    @FXML
    public void onConfirmButtonClick() {

        Alert confirmationAlert = new Alert(AlertType.INFORMATION);

        confirmationAlert.setHeaderText("Booking Successful"); // Optional: set header if you need it
        confirmationAlert.setContentText("Your parking spot is reserved. See you soon!");

        // Show the confirmation alert
        confirmationAlert.showAndWait();

        Stage currentStage = (Stage) reserveButton.getScene().getWindow();
        currentStage.close();

    }

    // Method to redirect to the userHomePage.fxml

}
