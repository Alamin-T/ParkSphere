package full.stack.parkspring;

import full.stack.parkspring.frontend.LoginController;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.repository.UserRepository;
import javafx.scene.control.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginControllerTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking UI components
        controller.usernameTextField = new TextField();
        controller.passwordTextField = new PasswordField();
        controller.invalidLoginMessage = new Label();
        controller.registerButton = new Label();
        controller.forgotPasswordButton = new Label();
    }

    @Test
    void testLoadAfterLoginPage_SuccessfulLogin() {
        // Arrange
        String email = "elsashyti@gmail.com";
        String password = "elsa";
        AppUser mockUser = new AppUser();
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        controller.usernameTextField.setText(email);
        controller.passwordTextField.setText(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // Act
        controller.loadAfterLoginPage();

        // Assert
        assertNull(controller.invalidLoginMessage.getText()); // No error message
    }

    @Test
    void testLoadAfterLoginPage_InvalidLogin() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";

        controller.usernameTextField.setText(email);
        controller.passwordTextField.setText(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        controller.loadAfterLoginPage();

        // Assert
        assertEquals("Invalid Login! Please try again.", controller.invalidLoginMessage.getText());
    }



    @Test
    void testOnLabelExitReg_StyleChange() {
        // Act
        controller.onLabelExitReg();

        // Assert
        assertEquals("-fx-font-size: 15px; -fx-text-fill: #868cf2; -fx-underline: false; -fx-font-family: 'Tw Cen MT'",
                controller.registerButton.getStyle());
    }

    @Test
    void testOnLabelHoverForgot_StyleChange() {
        // Act
        controller.onLabelHoverForgot();

        // Assert
        assertEquals("-fx-font-size: 13.4px; -fx-text-fill: #000ea8; -fx-underline: true; -fx-font-family: 'Tw Cen MT'",
                controller.forgotPasswordButton.getStyle());
    }

    @Test
    void testOnLabelExitForgot_StyleChange() {
        // Act
        controller.onLabelExitForgot();

        // Assert
        assertEquals("-fx-font-size: 13px; -fx-text-fill: #868cf2; -fx-underline: false; -fx-font-family: 'Tw Cen MT'",
                controller.forgotPasswordButton.getStyle());
    }
}
