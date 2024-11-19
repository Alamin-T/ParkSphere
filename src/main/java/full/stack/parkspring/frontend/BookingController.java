package full.stack.parkspring.frontend;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class BookingController {

    // UI Components
    @FXML
    private ComboBox<String> carComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ScrollPane timeSlotScrollPane;

    @FXML
    private Button confirmButton;

    @FXML
    private RadioButton regularRadioButton;

    @FXML
    private RadioButton vipRadioButton;

    @FXML
    private VBox slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17;

    @FXML
    private Label statusLabel1, statusLabel2, statusLabel3, statusLabel4, statusLabel5,
            statusLabel6, statusLabel7, statusLabel8, statusLabel9, statusLabel10, statusLabel11;

    // Variables for managing parking slots
    private VBox[] slots;
    private Label[] statusLabels;
    private VBox selectedSlot;

    // Variables for parking type and time slot selection
    private ToggleGroup parkingTypeGroup;
    private String selectedTimeSlot;

    /**
     * Initialization method, called after FXML components are loaded.
     */
    @FXML
    public void initialize() {
        // Set up the parking type toggle group
        parkingTypeGroup = new ToggleGroup();
        regularRadioButton.setToggleGroup(parkingTypeGroup);
        vipRadioButton.setToggleGroup(parkingTypeGroup);

        // Hide the progress bar initially
        progressBar.setVisible(false);

        // Initialize the slots and status labels arrays
        slots = new VBox[]{slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17};
        statusLabels = new Label[]{statusLabel1, statusLabel2, statusLabel3, statusLabel4,
                statusLabel5, statusLabel6, statusLabel7, statusLabel8,
                statusLabel9, statusLabel10, statusLabel11};

        // Apply drop shadow and mouse handlers to all slots
        DropShadow dropShadow = new DropShadow(10, 0, 5, Color.rgb(0, 0, 0, 0.4));
        for (VBox slot : slots) {
            applyDropShadowAndHandlers(slot, dropShadow);
        }
    }

    /**
     * Applies drop shadow and mouse click handlers to a parking slot.
     */
    @FXML
    private void applyDropShadowAndHandlers(VBox slot, DropShadow dropShadow) {
        if (slot != null) {
            slot.setEffect(dropShadow);
            slot.setOnMouseClicked(this::handleClick);
        }
    }

    /**
     * Handles the click event on a parking slot.
     */
    @FXML
    private void handleClick(MouseEvent event) {
        VBox clickedSlot = (VBox) event.getSource();
        Label timeSlotLabel = (Label) clickedSlot.getChildren().get(0);
        Label statusLabel = (Label) clickedSlot.getChildren().get(1);

        String timeSlot = timeSlotLabel.getText();
        String status = statusLabel.getText();

        if (selectedSlot == clickedSlot) {
            resetSlotAppearance(clickedSlot);
            selectedSlot = null;
        } else if ("Occupied".equals(status)) {
            showAlert("Error", "This time slot is taken and cannot be selected.");
        } else {
            if (selectedSlot != null) {
                resetSlotAppearance(selectedSlot);
            }

            changeSlotAppearance(clickedSlot);
            selectedSlot = clickedSlot;
            setSelectedTimeSlot(timeSlot);
            System.out.println("Time Slot Clicked: " + timeSlot);
        }
    }

    /**
     * Changes the appearance of a selected parking slot.
     */
    @FXML
    private void changeSlotAppearance(VBox slot) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), slot);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    /**
     * Resets the appearance of a deselected parking slot.
     */
    @FXML
    private void resetSlotAppearance(VBox slot) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), slot);
        scaleTransition.setFromX(1.1);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }

    /**
     * Handles the search button click, simulating a loading process.
     */
    @FXML
    private void handleSearchClick() {
        if (datePicker.getValue() != null) {
            progressBar.setVisible(true);

            new Thread(() -> {
                try {
                    Thread.sleep(1500); // Simulate loading time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                javafx.application.Platform.runLater(() -> {
                    timeSlotScrollPane.setVisible(true);
                    confirmButton.setVisible(true);
                    progressBar.setVisible(false);
                });
            }).start();
        } else {
            showAlert("Error", "Please select a date.");
        }
    }

    /**
     * Handles the confirm button click and transitions to the booking summary page.
     */
    @FXML
    public void handleConfirmClick(ActionEvent event) throws IOException {
        String selectedCar = carComboBox.getValue();
        String selectedDate = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "Not selected";
        RadioButton selectedParkingTypeButton = (RadioButton) parkingTypeGroup.getSelectedToggle();
        String selectedParkingType = (selectedParkingTypeButton != null) ? selectedParkingTypeButton.getText() : "Not selected";
        String timeSlot = selectedTimeSlot != null ? selectedTimeSlot : "Not selected";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_fxml/bookingSummary.fxml"));
        Parent summaryRoot = loader.load();

        BookingSummaryController summaryController = loader.getController();
        summaryController.setSummaryDetails(selectedCar, selectedParkingType, selectedDate, timeSlot);

        Stage newStage = new Stage();
        newStage.setTitle("Booking Summary");
        newStage.setScene(new Scene(summaryRoot));
        newStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
        newStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        newStage.showAndWait();
    }

    /**
     * Displays an alert message.
     */
    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Getters and Setters for selectedTimeSlot
    public void setSelectedTimeSlot(String time) {
        this.selectedTimeSlot = time;
    }

    public String getSelectedTimeSlot() {
        return selectedTimeSlot;
    }
}
