package full.stack.parkspring.frontend;

import full.stack.parkspring.config.UserSession;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.model.Vehicle;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    public BookingControllerService bookingControllerService;

    // UI Components
    @FXML
    public ComboBox<String> carComboBox;

    @FXML
    public DatePicker datePicker;

    @FXML
    public Label reserveLabel;

    @FXML
    public Line mBar1, mBar2, mBar3;

    @FXML
    public ProgressBar progressBar;

    @FXML
    public ScrollPane timeSlotScrollPane;

    @FXML
    public Button confirmButton;

    @FXML
    public RadioButton regularRadioButton;

    @FXML
    public RadioButton vipRadioButton;

    @FXML
    public VBox cancelButton;

    @FXML
    public Label homeButton;



    @FXML
    public VBox slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17;

    @FXML
    public Label statusLabel1, statusLabel2, statusLabel3, statusLabel4, statusLabel5,
            statusLabel6, statusLabel7, statusLabel8, statusLabel9, statusLabel10, statusLabel11;

    // Variables for managing parking slots
    public VBox[] slots;
    public Label[] statusLabels;
    public VBox selectedSlot;

    // Variables for parking type and time slot selection
    public ToggleGroup parkingTypeGroup;
    public String selectedTimeSlot;

    public void populateCarComboBox() {
        // Retrieve the logged-in user from the session
        AppUser loggedInUser = UserSession.getInstance().getLoggedInUser();

        if (loggedInUser != null) {
            long userId = loggedInUser.getId();
            fetchVehiclePlates(userId);
        } else {
            System.err.println("No user is currently logged in!");
        }
    }

    public void fetchVehiclePlates(long userId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/vehicles/user/" + userId + "/plates";
            ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                List<String> licensePlates = Arrays.asList(response.getBody());
                carComboBox.getItems().addAll(licensePlates);
            } else {
                System.err.println("Failed to fetch license plates: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching license plates.");
        }
    }

  /*  private String fetchCarModelByPlate(String licensePlate) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/vehicles/plate/" + licensePlate;
            ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("Fetched vehicle model: " + response.getBody().getModel());
                return response.getBody().getModel();
            } else {
                System.err.println("Failed to retrieve vehicle model. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching vehicle model.");
        }
        return "Unknown Model";
    }

*/


    /**
     * Initialization method, called after FXML components are loaded.
     */
    @FXML
    public void initialize() {

        populateCarComboBox();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> handleDateChange());


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




    @FXML
    public void handleDateChange() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            markSlotsRandomlyBasedOnDate(selectedDate);
        }
    }

    /**
     * Randomly marks slots as occupied or available based on the selected date.
     */
    public void markSlotsRandomlyBasedOnDate(LocalDate date) {
        Random random = new Random(date.hashCode()); // Seed based on date for consistency

        for (int i = 0; i < slots.length; i++) {
            boolean isOccupied = random.nextBoolean(); // Randomly decide if the slot is occupied
            Label statusLabel = (Label) slots[i].getChildren().get(1); // Assuming statusLabel is the second child

            if (isOccupied) {
                statusLabel.setText("Occupied");
                slots[i].setStyle("-fx-background-color: red;");
            } else {
                statusLabel.setText("Available");
                slots[i].setStyle("-fx-background-color: green;");
            }
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
    public void handleClick(MouseEvent event) {
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
    public void changeSlotAppearance(VBox slot) {
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
                    Thread.sleep(1000); // Simulate loading time
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

    @FXML
    public void homeButtonOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/userHomePage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading home page");
        }
    }

    @FXML
    public void onLabelHoverHome() {
        homeButton.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitHoverHome() {
        homeButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }


    @FXML
    public void onLabelHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }


    public void cancelButtonOnAction()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/userHomePage.fxml"));
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

    public void onPolylineHover() {
        cancelButton.setStyle("-fx-stroke: #000ea8; -fx-stroke-width: 4px;");
    }

    public void onPolylineExit() {
        cancelButton.setStyle("-fx-stroke: #8589f1; -fx-stroke-width: 2px;");
    }

    @FXML
    public void handleConfirmClick(ActionEvent event) throws IOException {
        String selectedCar = carComboBox.getValue();
     //   String model = fetchCarModelByPlate(selectedCar);
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

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        cancelButtonOnAction();
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
