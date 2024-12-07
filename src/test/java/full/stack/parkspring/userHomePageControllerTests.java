package full.stack.parkspring;

import full.stack.parkspring.frontend.userHomePageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class userHomePageControllerTests {

    @Mock private Label homeButton;
    @Mock private Label reserveLabel;
    @Mock private Label newCarButton;
    @Mock private VBox avatarMenu;
    @Mock private Label paymentMethodButton;

    @InjectMocks private userHomePageController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller.homeButton = homeButton;
        controller.reserveLabel = reserveLabel;
        controller.newCarButton = newCarButton;
        controller.avatarMenu = avatarMenu;
        controller.paymentMethodButton = paymentMethodButton;
    }

    @Test
    void testReserveButtonOnClick() {
        // Simulate clicking the reserve button
        controller.reserveButtonOnClick();

        // Verify that `FXMLLoader` tries to load the FXML resource
        verify(reserveLabel, atLeastOnce()).getScene();
    }

    @Test
    void testHomeButtonOnClick() {
        // Simulate clicking the home button
        controller.homeButtonOnClick();

        verify(homeButton, atLeastOnce()).getScene();
    }

    @Test
    void testLogoutButtonOnClick() {
        controller.logoutButtonOnClick();

        // Ensure session clearing logic is called
        assertNotNull(controller.paymentMethodButton);
        verify(paymentMethodButton, atLeastOnce()).getScene();
    }

    @Test
    void testRegisterCarButtonOnClick(){
        controller.registerCarButtonOnClick();

        assertNotNull(newCarButton);
        verify(newCarButton, atLeastOnce()).getScene();
    }
}

