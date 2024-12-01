
package full.stack.parkspring.frontend;
import full.stack.parkspring.config.SpringFXMLLoader;
import full.stack.parkspring.config.UserSession;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.model.LicenseClass;
import full.stack.parkspring.model.PowerType;
import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.repository.UserRepository;
import full.stack.parkspring.repository.VehicleRepository;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;



@Controller
public class registerNewCarController {

    @FXML
    private TextField CarModelField;

    @FXML
    private ComboBox<String> LicenseClassComboBox;

    @FXML
    private TextField ColorField;

    @FXML
    private TextField LicenseNumberField;

    @FXML
    private TextField LicensePlateField;

    @FXML
    private TextField NameField;

    @FXML
    private Button RegisterButton;

    @FXML
    private VBox avatarMenu;

    @FXML
    private Label homeButton;

    @FXML
    private Line mBar1;

    @FXML
    private Line mBar2;

    @FXML
    private Line mBar3;

    @FXML
    private Label menuBars;

    @FXML
    private ImageView profileAvatarIcon;

    @FXML
    private Label reserveLabel;

    @FXML
    private TextField searchBarField;

    @FXML
    private Label signInButton;

    @FXML
    private ToggleGroup powerTypeToggleGroup;

    @FXML
    private RadioButton petrolDieselRadioButton;

    @FXML
    private RadioButton electricRadioButton;

    @FXML
    private RadioButton hybridRadioButton;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;






    @FXML
    public void initialize() {

        ObservableList<String> licenseClasses = FXCollections.observableArrayList(
                "Private", "Public/Taxi", "Commercial", "Rental", "Diplomatic", "Emergency/Police"
        );

        LicenseClassComboBox.setItems(licenseClasses);

        powerTypeToggleGroup = new ToggleGroup();
        petrolDieselRadioButton.setToggleGroup(powerTypeToggleGroup);
        electricRadioButton.setToggleGroup(powerTypeToggleGroup);
        hybridRadioButton.setToggleGroup(powerTypeToggleGroup);


        avatarMenu.setVisible(false);
        for (Node child : avatarMenu.getChildren()) {
            if (child instanceof Label) {
                Label label = (Label) child;
                label.setOnMouseEntered(event -> label.setStyle("-fx-background-color: #9da0f3; -fx-text-fill: #000000;"));
                label.setOnMouseExited(event -> label.setStyle(""));
            }
        }

    }


/*
    @FXML
    public void onRegisterButtonClick() {
        String carModel = CarModelField.getText();
        String licenseNumber = LicenseNumberField.getText();
        String licensePlate = LicensePlateField.getText();
        String ownerName = NameField.getText();
        String licenseClass = LicenseClassComboBox.getValue();
        String color = ColorField.getText();

        // Get the selected power type from the ToggleGroup
        RadioButton selectedRadioButton = (RadioButton) powerTypeToggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            showAlert("Error", "Please select a Power Type!");
            return;
        }
        String selectedPowerTypeText = selectedRadioButton.getText();

        if (carModel.isEmpty() || licensePlate.isEmpty() || ownerName.isEmpty() || licenseClass == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        try {
            PowerType powerType = PowerType.fromDisplayName(selectedPowerTypeText);

            // Retrieve the logged-in user from the session
            AppUser loggedInUser = UserSession.getInstance().getLoggedInUser();

            if (loggedInUser == null) {
                showAlert("Error", "No logged-in user found. Please log in again.");
                return;
            }

            Vehicle vehicle = Vehicle.builder()
                    .plate(licensePlate)
                    .licenseNumber(licenseNumber)
                    .powerType(powerType)
                    .licenseClass(LicenseClass.valueOf(licenseClass.toUpperCase()))
                    .color(color)
                    .user(loggedInUser) // Associate the vehicle with the logged-in user
                    .build();

            // Save the vehicle to the database
            vehicleRepository.save(vehicle);

            // Show success alert
            showAlert("Success", "Vehicle registered successfully!");
        } catch (IllegalArgumentException e) {
            showAlert("Error", "Invalid License Class or Power Type!");
        }
    }
*/

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {
        // Get the logged-in user
        AppUser user = UserSession.getInstance().getLoggedInUser();

        if (user == null) {
            showAlert("Error", "No user is logged in. Please log in to register a car.");
            return;
        }

        try {
            // Gather inputs
            String carModel = CarModelField.getText().trim();
            String licensePlate = LicensePlateField.getText().trim();
            String licenseNumber = LicenseNumberField.getText().trim();
            String color = ColorField.getText().trim();
            String selectedLicenseClass = LicenseClassComboBox.getValue();
            RadioButton selectedPowerTypeRadioButton = (RadioButton) powerTypeToggleGroup.getSelectedToggle();

            // Validate inputs
            if (carModel.isEmpty() || licensePlate.isEmpty() || licenseNumber.isEmpty() || color.isEmpty() ||
                    selectedLicenseClass == null || selectedPowerTypeRadioButton == null) {
                showAlert("Error", "All fields are required!");
                return;
            }

            // Map inputs to enums
            LicenseClass licenseClass = LicenseClass.fromDisplayName(selectedLicenseClass);
            PowerType powerType = PowerType.fromDisplayName(selectedPowerTypeRadioButton.getText());

            // Build the Vehicle object
            Vehicle vehicle = Vehicle.builder()
                    .model(carModel)
                    .plate(licensePlate)
                    .licenseNumber(licenseNumber)
                    .color(color)
                    .licenseClass(licenseClass)
                    .powerType(powerType)
                    .user(user)
                    .build();

            // Create a RestTemplate for making the API request
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "http://localhost:8080/api/vehicles/register"; // Replace with your actual API endpoint

            // Send the vehicle object to the backend via a POST request
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, vehicle, String.class);

            // Handle the response from the API
            if (response.getStatusCode().is2xxSuccessful()) {
                showAlert("Success", "Vehicle registered successfully!");
            } else {
                showAlert("Error", "Vehicle registration failed: " + response.getBody());
            }

            // Clear form fields
            CarModelField.clear();
            LicensePlateField.clear();
            LicenseNumberField.clear();
            ColorField.clear();
            LicenseClassComboBox.setValue(null);
            powerTypeToggleGroup.selectToggle(null);

        } catch (IllegalArgumentException e) {
            showAlert("Error", "Invalid input: " + e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @FXML
    public void reserveButtonOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/reserveBooking.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) reserveLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading reserve booking window");
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
    public void SignInButtonOnClick() {
        try {
            // Load the login FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/login.fxml"));
            Parent loginRoot = fxmlLoader.load();

            // Initialize the login stage
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");

            Scene loginScene = new Scene(loginRoot);
            loginStage.setScene(loginScene);

            // Set the login stage modality (blocks interaction with other windows)
            loginStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
            loginStage.initOwner(signInButton.getScene().getWindow());

            // Show the login stage and wait for it to close before proceeding
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading login window");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected error occurred while opening login stage");
        }
    }

    @FXML
    private void showMenu() {
        avatarMenu.setVisible(true);
    }

    @FXML
    private void toggleMenu() {
        double moveAmount = 7;

        if (!avatarMenu.isVisible()) {
            showMenuWithAnimation();
            mBar1.setLayoutX(mBar1.getLayoutX() + moveAmount);
            mBar3.setLayoutX(mBar3.getLayoutX() - moveAmount);
            setMenuBarStyle("#000000", 4);
        } else {
            hideMenuWithAnimation();
            mBar1.setLayoutX(mBar1.getLayoutX() - moveAmount);
            mBar3.setLayoutX(mBar3.getLayoutX() + moveAmount);
            setMenuBarStyle("white", 2.5);
        }
    }

    @FXML
    private void hideMenu() {
        avatarMenu.setVisible(false);
    }

    @FXML
    private void showMenuWithAnimation() {
        avatarMenu.setVisible(true);

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), avatarMenu);
        slideIn.setFromY(-avatarMenu.getPrefHeight());
        slideIn.setToY(0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), avatarMenu);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        ParallelTransition parallelTransition = new ParallelTransition(slideIn, fadeIn);
        parallelTransition.setInterpolator(Interpolator.EASE_BOTH);
        parallelTransition.play();
    }

    private void hideMenuWithAnimation() {
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(300), avatarMenu);
        slideOut.setFromY(0);
        slideOut.setToY(-avatarMenu.getPrefHeight());

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), avatarMenu);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        ParallelTransition parallelTransition = new ParallelTransition(slideOut, fadeOut);
        parallelTransition.setInterpolator(Interpolator.EASE_BOTH);
        parallelTransition.setOnFinished(event -> avatarMenu.setVisible(false));
        parallelTransition.play();
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
    public void onPolylineHover() {
        setPolylineStyle("#000000", 4);
    }

    @FXML
    public void onPolylineExit() {
        setPolylineStyle("white", 2.5);
    }

    @FXML
    public void onLabelHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }

    private void setMenuBarStyle(String color, double width) {
        mBar1.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
        mBar2.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
        mBar3.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
    }

    private void setPolylineStyle(String color, double width) {
        mBar1.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar2.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar3.setStrokeLineCap(StrokeLineCap.ROUND);

        mBar1.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
        mBar2.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
        mBar3.setStyle("-fx-stroke: " + color + "; -fx-stroke-width: " + width + "px;");
    }

    // Utility method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}