package full.stack.parkspring.frontend;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HomePageController {

    @FXML private Button joinUsButton;
    @FXML private Label signInButton;
    @FXML private ImageView profileAvatarIcon;
    @FXML private TextField searchBarField;
    @FXML private VBox avatarMenu;
    @FXML private Line mBar1, mBar2, mBar3;

    @FXML
    public void SignInButtonOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller_fxml/login.fxml"));
            Parent loginRoot = fxmlLoader.load();

            // Initialize the login stage
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot));
            loginStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
            loginStage.initOwner(signInButton.getScene().getWindow());

            // Pass both the login stage and the main application stage
            LoginController loginController = fxmlLoader.getController();
            loginController.setLoginStage(loginStage);
            loginController.setMainStage((Stage) signInButton.getScene().getWindow());

            // Show the login stage and wait for it to close
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void onPolylineHover() {
        setPolylineStyle("#000000", 4);
    }

    @FXML
    public void onPolylineExit() {
        setPolylineStyle("white", 2.5);
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
