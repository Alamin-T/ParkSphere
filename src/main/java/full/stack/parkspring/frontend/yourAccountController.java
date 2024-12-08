package full.stack.parkspring.frontend;

import full.stack.parkspring.config.UserSession;
import full.stack.parkspring.model.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;

public class yourAccountController {

    @FXML
    private Polyline cancelButton;

    @FXML
    private Label emailField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label genderField;

    @FXML
    private Label homeButton;

    @FXML
    private Label lastNameField;

    @FXML
    private Label reserveLabel;

    @FXML
    private Label usernameField;

    @FXML
    public void initialize() {
        UserSession userSession = UserSession.getInstance();
        AppUser loggedInUser = userSession.getLoggedInUser();

        if (loggedInUser != null) {
            String[] nameParts = loggedInUser.getUsername().split(" ", 2);
            if (nameParts.length == 2) {
                firstNameField.setText(nameParts[0]);
                lastNameField.setText(nameParts[1]);
            } else if (nameParts.length == 1) {
                firstNameField.setText(nameParts[0]); // only first name is available
                lastNameField.setText(""); // or handle as needed
            } else {
                firstNameField.setText("");
                lastNameField.setText("");
            }
            usernameField.setText(loggedInUser.getUsername());
            emailField.setText(loggedInUser.getEmail());
            genderField.setText(loggedInUser.getGender().toString());
        }
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
    public void onLabelHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }
}
