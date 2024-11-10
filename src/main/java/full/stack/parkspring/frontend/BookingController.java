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

    public Button confirmButton;
    private ToggleGroup parkingTypeGroup;

    @FXML
    private ComboBox<String> carComboBox;

    @FXML
    public RadioButton regularRadioButton;

    @FXML
    public RadioButton vipRadioButton;

    @FXML
    private ScrollPane timeSlotScrollPane;

    public void setSelectedTimeSlot(String time){
        this.selectedTimeSlot = time;
    }

    public String getSelectedTimeSlot(){
        return selectedTimeSlot;
    }

    public void onTimeSlotSelected(String time){
        setSelectedTimeSlot(time);
    }
    // Add this declaration

    @FXML
    private String selectedTimeSlot;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ProgressBar progressBar; // Progress bar to show loading status

    @FXML
    private VBox slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17;

    @FXML
    private Label statusLabel1, statusLabel2, statusLabel3, statusLabel4, statusLabel5,
            statusLabel6, statusLabel7, statusLabel8, statusLabel9, statusLabel10, statusLabel11; // Declare status labels

    private VBox[] slots; // Store all slots in an array for easier management
    private Label[] statusLabels; // Store all status labels in an array for easier management
    private VBox selectedSlot;

    @FXML
    public void initialize() {

        // Initially hide the time slot ScrollPane and progress bar
        progressBar.setVisible(false); // Initially hide the progress bar

        // Initialize the slots and status labels
        slots = new VBox[]{slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17};
        statusLabels = new Label[]{statusLabel1, statusLabel2, statusLabel3, statusLabel4,
                statusLabel5, statusLabel6, statusLabel7, statusLabel8,
                statusLabel9, statusLabel10, statusLabel11, statusLabel2, statusLabel3, statusLabel4, statusLabel5, statusLabel6, statusLabel7};

        DropShadow dropShadow = new DropShadow(10, 0, 5, Color.rgb(0, 0, 0, 0.4));

        for (VBox slot : slots) {
            applyDropShadowAndHandlers(slot, dropShadow);


        }

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.BLACK);
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setRadius(10);

        parkingTypeGroup = new ToggleGroup();
        // Add the radio buttons to the group
        regularRadioButton.setToggleGroup(parkingTypeGroup);
        vipRadioButton.setToggleGroup(parkingTypeGroup);



    }

    @FXML
    private void applyDropShadowAndHandlers(VBox slot, DropShadow dropShadow) {
        if (slot != null) { // Check for null to prevent NullPointerException
            slot.setEffect(dropShadow);
            slot.setOnMouseClicked(this::handleClick);
        }
    }

    @FXML
    private void handleClick(MouseEvent event) {
        VBox clickedSlot = (VBox) event.getSource();
        Label timeSlotLabel = (Label) clickedSlot.getChildren().get(0);
        Label statusLabel = (Label) clickedSlot.getChildren().get(1);

        String timeSlot = timeSlotLabel.getText();
        String status = statusLabel.getText();

        // Check if clicked slot is already selected
        if (selectedSlot == clickedSlot) {
            resetSlotAppearance(clickedSlot);
            selectedSlot = null;
        } else {
            if ("Occupied".equals(status)) {
                showAlert("Error", "This time slot is taken and cannot be selected.");
            } else {
                if (selectedSlot != null) {
                    resetSlotAppearance(selectedSlot);
                }

                changeSlotAppearance(clickedSlot);
                selectedSlot = clickedSlot;

                // This sets the selected time slot
                setSelectedTimeSlot(timeSlot);
                System.out.println("Time Slot Clicked: " + timeSlot);
            }
        }
    }




    @FXML
    private void changeSlotAppearance(VBox slot) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), slot);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    @FXML
    private void resetSlotAppearance(VBox slot) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), slot);
        scaleTransition.setFromX(1.1);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }

    @FXML
    private void handleSearchClick() {

        if (datePicker.getValue() != null) {
            progressBar.setVisible(true); // Show the progress bar
            // Simulate loading for 2.5 seconds
            new Thread(() -> {
                try {
                    Thread.sleep(1500); // Simulate loading time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Update UI on the JavaFX Application Thread
                javafx.application.Platform.runLater(() -> {
                    timeSlotScrollPane.setVisible(true);
                    confirmButton.setVisible(true);// Make the ScrollPane visible
                    progressBar.setVisible(false); // Hide the progress bar
                });
            }).start();
        } else {
            showAlert("Error", "Please select a date.");
        }



    }

    @FXML
    public void handleTimeSlotClick(Label statusLabel) {
        selectedTimeSlot = statusLabel.getText();
    }

    @FXML
    public void handleConfirmClick(ActionEvent event) throws IOException {
        // Get the selected car
        String selectedCar = carComboBox.getValue();

        // Get the selected date
        String selectedDate = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "Not selected";

        // Get the selected parking type (VIP or Regular)
        RadioButton selectedParkingTypeButton = (RadioButton) parkingTypeGroup.getSelectedToggle();
        String selectedParkingType = (selectedParkingTypeButton != null) ? selectedParkingTypeButton.getText() : "Not selected";

        // Get the selected time slot (set when a time slot is clicked)
        String timeSlot = selectedTimeSlot != null ? selectedTimeSlot : "Not selected";

        // Load the booking summary page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingSummary.fxml"));
        Parent summaryRoot = loader.load();

        // Pass the data to the summary page controller
        BookingSummaryController summaryController = loader.getController();
        summaryController.setSummaryDetails(selectedCar, selectedParkingType, selectedDate, timeSlot);

        // Create a new Stage for the booking summary
        Stage newStage = new Stage();
        newStage.setTitle("Booking Summary");
        newStage.setScene(new Scene(summaryRoot));

        // Set the modality to block events from the owner window
        newStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
        newStage.initOwner(((Node) event.getSource()).getScene().getWindow());

        // Show the new Stage and wait for it to close before returning
        newStage.showAndWait();
    }



    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}