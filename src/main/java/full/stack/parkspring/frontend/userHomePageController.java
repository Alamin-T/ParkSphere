package full.stack.parkspring.frontend;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class userHomePageController {

    @FXML private Label homeButton, reserveLabel, newCarButton, logoutButton;
    @FXML private TextField searchBarField;
    @FXML private VBox avatarMenu;
    @FXML private Line mBar1, mBar2, mBar3;
    @FXML private Label paymentMethodButton;

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
    public void logoutButtonOnClick() {
        try {
            // Load the home page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/homePage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            // Introduce a delay before showing the alert
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 1-second delay
            pause.setOnFinished(event -> {
                // Show logout success message after the delay
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Logout Successful");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully logged out. See you next time!");
                alert.show();
            });
            pause.play(); // Start the delay

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading home page");
        }
    }


    @FXML
    public void registerCarButtonOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/registerNewCar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) newCarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading home page");
        }
    }


    @FXML
    public void paymentButtonOnClick() {
        try {
            // Load the payment method page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/addPaymentMethod.fxml"));
            Parent root = fxmlLoader.load();

            // Initialize the payment stage
            Stage paymentStage = new Stage();
            paymentStage.setTitle("Add Payment Method");

            // Set the scene for the payment stage
            Scene paymentScene = new Scene(root);
            paymentStage.setScene(paymentScene);

            // Show and wait for the payment stage to be closed before proceeding
            paymentStage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // Makes the new window modal
            paymentStage.initOwner(paymentMethodButton.getScene().getWindow()); // Sets the owner window
            paymentStage.showAndWait(); // Waits for the user to close the payment window before returning control

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading payment page");
        }
    }


    @FXML
    public void YourCarsButtonOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/registerNewCar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) newCarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading home page");
        }
    }


    @FXML
    public void initialize() {
        avatarMenu.setVisible(false);

        // Add hover effect on avatar menu labels
        for (Node child : avatarMenu.getChildren()) {
            if (child instanceof Label) {
                Label label = (Label) child;
                label.setOnMouseEntered(event -> label.setStyle("-fx-background-color: #9da0f3; -fx-text-fill: #000000;"));
                label.setOnMouseExited(event -> label.setStyle(""));
            }
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
}