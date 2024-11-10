package full.stack.parkspring.frontend;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class HomePageController {

    @FXML
    private Button joinUsButton;

    @FXML
    private Label homeButton;

    @FXML
    private ImageView profileAvatarIcon;

    @FXML
    private TextField searchBarField;

    @FXML
    private Label signInButton;

    @FXML
    private VBox avatarMenu;

    @FXML
    private Label menuBars;

    @FXML
    private Line mBar1;

    @FXML
    private Line mBar2;

    @FXML
    private Line mBar3;

    @FXML
    private Label reserveLabel;

    @FXML
    public void reserveButtonOnClick() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reserveBooking.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) reserveLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void homeButtonOnClick() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void onPolylineHover() {
        mBar1.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar2.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar3.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar1.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
        mBar2.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
        mBar3.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
    }

    @FXML
    public void onPolylineExit() {
        mBar1.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar2.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar3.setStrokeLineCap(StrokeLineCap.ROUND);
        mBar1.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");
        mBar2.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");
        mBar3.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");

    }

    @FXML
    public void onLabelHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void onLabelExitHoverReserve() {
        reserveLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-underline: false; -fx-font-family: 'Tw Cen MT'");
    }

    @FXML
    public void SignInButtonOnClick() throws IOException {
        // Load login.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = fxmlLoader.load();

        // Create a new stage for the login window
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");

        // Set the scene for the login stage
        Scene loginScene = new Scene(loginRoot);
        loginStage.setScene(loginScene);

        // Make the loginStage modal (blocks interaction with other windows)
        loginStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
        loginStage.initOwner(signInButton.getScene().getWindow());  // Set the home page as the owner of the login stage

        // Show the login window and make homePage unresponsive
        loginStage.showAndWait();
    }

    @FXML
    public void initialize() {
        // Set hover behavior to display the menu
        avatarMenu.setVisible(false);
        // Ensure the menu is initially hidden
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
        avatarMenu.setVisible(true);  // Make the menu visible
    }



    @FXML
    private void toggleMenu() {
        double moveAmount = 7;

        if (!avatarMenu.isVisible()) {
            showMenuWithAnimation();  // Show menu with drop-down effect
            mBar1.setLayoutX(mBar1.getLayoutX() + moveAmount);
            mBar3.setLayoutX(mBar3.getLayoutX() - moveAmount);
            mBar1.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
            mBar2.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
            mBar3.setStyle("-fx-stroke: #000000; -fx-stroke-width: 4px;");
        } else {
            hideMenuWithAnimation();  // Hide menu with slide-up effect
            mBar1.setLayoutX(mBar1.getLayoutX() - moveAmount);
            mBar3.setLayoutX(mBar3.getLayoutX() + moveAmount);
            mBar1.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");
            mBar2.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");
            mBar3.setStyle("-fx-stroke: white; -fx-stroke-width: 2.5px;");
        }
    }



    @FXML
    private void hideMenu() {
        avatarMenu.setVisible(false);  // Hide the menu when the mouse exits
    }

    @FXML
    private void showMenuWithAnimation() {
        avatarMenu.setVisible(true);

        // Slide down animation
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), avatarMenu);
        slideIn.setFromY(-avatarMenu.getPrefHeight());  // Start from above the top of the screen
        slideIn.setToY(0);  // End at the original position

        // Fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), avatarMenu);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // Play both animations together
        ParallelTransition parallelTransition = new ParallelTransition(slideIn, fadeIn);
        parallelTransition.setInterpolator(Interpolator.EASE_BOTH);
        parallelTransition.play();
    }


    private void hideMenuWithAnimation() {
        // Slide up animation
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(300), avatarMenu);
        slideOut.setFromY(0);  // Start from its current position
        slideOut.setToY(-avatarMenu.getPrefHeight());  // Move it up and out of the screen

        // Fade out animation
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), avatarMenu);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Play both animations together
        ParallelTransition parallelTransition = new ParallelTransition(slideOut, fadeOut);
        parallelTransition.setInterpolator(Interpolator.EASE_BOTH);

        parallelTransition.setOnFinished(event -> avatarMenu.setVisible(false));
        parallelTransition.play();
    }



}