package full.stack.parkspring.frontend;

import full.stack.parkspring.config.UserSession;
import full.stack.parkspring.model.AppUser;
import javafx.animation.*;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class userHomePageController {

    @FXML
    public Label homeButton, reserveLabel, newCarButton, logoutButton;
    @FXML
    public TextField searchBarField;
    @FXML
    public VBox avatarMenu;
    @FXML
    public Line mBar1, mBar2, mBar3;
    @FXML
    public Label paymentMethodButton;
    @FXML
    private ImageView  parkGarage, parkAccount, parkPayment, parkReg;
    @FXML
    private ImageView  parkContact, parkRate, parkMap;
    @FXML
    private Label labelForParkMap, labelForParkContact, labelForParkRate;
    @FXML
    private Label headerForParkContact, headerForParkMap, headerForParkRate;
    @FXML
    private Label tooltipParkGarage, tooltipParkAccount, tooltipParkPayment, tooltipParkReg;
    @FXML
    private Label welcomeLabel;


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
            System.out.println("Loading FXML from: " + getClass().getResource("/controller_fxml/reserveBooking.fxml"));
        }

    }

    @FXML
    public void reserveCarImage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/reserveBooking.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) parkGarage.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading home page");
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
            // Clear user session
            UserSession.getInstance().clearSession();

            // Load the home page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/homePage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            // Introduce a delay before showing the alert
            PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
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
            Stage stage = (Stage) reserveLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading reserve booking window");
        }
    }

    @FXML
    public void registerCarImage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/registerNewCar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) parkReg.getScene().getWindow();
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
    public void initialize() {

        AppUser loggedInUser = UserSession.getInstance().getLoggedInUser();
        if (loggedInUser != null) {
            String username = loggedInUser.getUsername();
            welcomeLabel.setText("Welcome " + username + ",");
        } else {
            welcomeLabel.setText("Welcome, Guest!");

        }
        avatarMenu.setVisible(false);
        for (Node child : avatarMenu.getChildren()) {
            if (child instanceof Label) {
                Label label = (Label) child;
                label.setOnMouseEntered(event -> label.setStyle("-fx-background-color: #9da0f3; -fx-text-fill: #000000;"));
                label.setOnMouseExited(event -> label.setStyle(""));
            }
        }

        // Set hover effects on the images with tooltip visibility toggling for some images
        setupHoverEffect(parkGarage, tooltipParkGarage, 1.05, true);  // Garage has tooltip
        setupHoverEffect(parkReg, tooltipParkReg, 1.1, true);         // Registration has tooltip
        setupHoverEffect(parkAccount, tooltipParkAccount, 1.1, true); // Account has tooltip
        setupHoverEffect(parkPayment, tooltipParkPayment, 1.1, true); // Payment has tooltip

        // Set hover effects on the images without tooltip visibility toggling for others
        setupHoverEffect(parkMap, null, 1.15, false);    // Map only scales
        setupHoverEffect(parkRate, null, 1.15, false);   // Rates only scales
        setupHoverEffect(parkContact, null, 1.15, false);
        // Contact only scales

        setupHoverAnimation(parkMap, labelForParkMap, "Downtown Parking Center\nGreenwood Plaza Parking\nSeaside Parking Garage", headerForParkMap, "Our Locations");
        setupHoverAnimation(parkContact, labelForParkContact, "support@parksphere.com\nPhone: +36 1 846 9420\nFax: +36 1 833 0239", headerForParkContact, "How to contact us");
        setupHoverAnimation(parkRate, labelForParkRate, "Mon to Thu(7AM-11PM)\n-Reg: $3.00/hr|VIP: $5.00/hr\nFri to Sat(8AM-8PM)\n-Reg: $4.59/hr|VIP: $7.69/hr", headerForParkRate, "Parking fares");
    }

    private void setupHoverAnimation(ImageView imageView, Label regularLabel, String regularLabelText, Label headerLabel, String headerLabelText) {
        regularLabel.setText(regularLabelText); // Set the regular label text
        headerLabel.setText(headerLabelText);   // Set the header label text

        regularLabel.setVisible(false);         // Initially hide the regular label
        headerLabel.setVisible(false);          // Initially hide the header label

        imageView.setOnMouseEntered(event -> {
            regularLabel.setVisible(true);
            headerLabel.setVisible(true);

            // Translate Transition for sliding in the regular label from the right
            TranslateTransition slideInRegular = new TranslateTransition(Duration.millis(300), regularLabel);
            slideInRegular.setFromX(regularLabel.getWidth());
            slideInRegular.setToX(0);

            // Translate Transition for sliding in the header label from the right
            TranslateTransition slideInHeader = new TranslateTransition(Duration.millis(300), headerLabel);
            slideInHeader.setFromX(headerLabel.getWidth());
            slideInHeader.setToX(0);

            // Fade Transition to fade in the regular label
            FadeTransition fadeInRegular = new FadeTransition(Duration.millis(300), regularLabel);
            fadeInRegular.setFromValue(0.0);
            fadeInRegular.setToValue(1.0);

            // Fade Transition to fade in the header label
            FadeTransition fadeInHeader = new FadeTransition(Duration.millis(300), headerLabel);
            fadeInHeader.setFromValue(0.0);
            fadeInHeader.setToValue(1.0);

            // Scale transition for the image
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), imageView);
            scaleUp.setToX(1.15);
            scaleUp.setToY(1.15);

            ParallelTransition parallelTransition = new ParallelTransition(slideInRegular, slideInHeader, fadeInRegular, fadeInHeader, scaleUp);
            parallelTransition.setInterpolator(Interpolator.EASE_BOTH);
            parallelTransition.play();
        });

        imageView.setOnMouseExited(event -> {
            // Translate Transition for sliding out the regular label to the right
            TranslateTransition slideOutRegular = new TranslateTransition(Duration.millis(300), regularLabel);
            slideOutRegular.setFromX(0);
            slideOutRegular.setToX(regularLabel.getWidth());

            // Translate Transition for sliding out the header label to the right
            TranslateTransition slideOutHeader = new TranslateTransition(Duration.millis(300), headerLabel);
            slideOutHeader.setFromX(0);
            slideOutHeader.setToX(headerLabel.getWidth());

            // Fade Transition to fade out the regular label
            FadeTransition fadeOutRegular = new FadeTransition(Duration.millis(300), regularLabel);
            fadeOutRegular.setFromValue(1.0);
            fadeOutRegular.setToValue(0.0);

            // Fade Transition to fade out the header label
            FadeTransition fadeOutHeader = new FadeTransition(Duration.millis(300), headerLabel);
            fadeOutHeader.setFromValue(1.0);
            fadeOutHeader.setToValue(0.0);

            // Scale transition to reset the image size
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), imageView);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);

            ParallelTransition parallelTransition = new ParallelTransition(slideOutRegular, slideOutHeader, fadeOutRegular, fadeOutHeader, scaleDown);
            parallelTransition.setInterpolator(Interpolator.EASE_BOTH);
            parallelTransition.setOnFinished(e -> {
                regularLabel.setVisible(false);   // Hide regular label after animation
                headerLabel.setVisible(false);    // Hide header label after animation
            });
            parallelTransition.play();
        });
    }


    private void setupHoverEffect(ImageView imageView, Label tooltip, double scaleFactor, boolean showTooltip) {
        // Ensure the tooltip is not null and set its visibility to false initially
        if (showTooltip && tooltip != null) {
            tooltip.setVisible(false);  // Make sure it's hidden initially
        }

        imageView.setOnMouseEntered(event -> {
            // Scale the image
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), imageView);
            scaleUp.setToX(scaleFactor);
            scaleUp.setToY(scaleFactor);
            scaleUp.play();

            // Show the tooltip when the image is hovered (only if showTooltip is true)
            if (showTooltip && tooltip != null) {
                tooltip.setVisible(true); // Show tooltip on hover
            }
        });

        imageView.setOnMouseExited(event -> {
            // Reset the image scale
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), imageView);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            scaleDown.play();

            // Hide the tooltip when the hover ends (only if showTooltip is true)
            if (showTooltip && tooltip != null) {
                tooltip.setVisible(false); // Hide tooltip when hover ends
            }
        });
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
