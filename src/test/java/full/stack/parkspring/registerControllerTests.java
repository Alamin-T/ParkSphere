
package full.stack.parkspring;

import full.stack.parkspring.frontend.registerController;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class registerControllerTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private registerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetInvalidRegisterMessageOnAction_SuccessfulRegistration() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setEmail("test@example.com");
        mockUser.setUsername("TestUser");
        mockUser.setPassword("password123");
        mockUser.setGender(Gender.MALE);

        ResponseEntity<String> mockResponse = new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), eq(mockUser), eq(String.class)))
                .thenReturn(mockResponse);

        // Act
        controller.setInvalidRegisterMessageOnAction(null); // Mocking the actual event

        // Assert
        assertEquals("Registration successful!", controller.invalidLoginMessage.getText());
    }

    @Test
    void testSetInvalidRegisterMessageOnAction_EmptyFields() {
        // Arrange
        controller.firstNameField.setText("");
        controller.lastNameField.setText("");
        controller.emailField.setText("");
        controller.enterPasswordField.setText("");
        controller.reEnterPasswordField.setText("");

        // Act
        controller.setInvalidRegisterMessageOnAction(null);

        // Assert
        assertEquals("Please fill in all fields.", controller.invalidLoginMessage.getText());
    }

    @Test
    void testSetInvalidRegisterMessageOnAction_PasswordMismatch() {
        // Arrange
        controller.firstNameField.setText("John");
        controller.lastNameField.setText("Doe");
        controller.emailField.setText("john.doe@example.com");
        controller.enterPasswordField.setText("password123");
        controller.reEnterPasswordField.setText("password456");

        // Act
        controller.setInvalidRegisterMessageOnAction(null);

        // Assert
        assertEquals("Passwords do not match.", controller.invalidLoginMessage.getText());
    }

    @Test
    void testSetInvalidRegisterMessageOnAction_ApiCallFails() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setEmail("test@example.com");
        mockUser.setUsername("TestUser");
        mockUser.setPassword("password123");
        mockUser.setGender(Gender.MALE);

        ResponseEntity<String> mockResponse = new ResponseEntity<>("Error: User already exists", HttpStatus.BAD_REQUEST);
        when(restTemplate.postForEntity(anyString(), eq(mockUser), eq(String.class)))
                .thenReturn(mockResponse);

        // Act
        controller.setInvalidRegisterMessageOnAction(null);

        // Assert
        assertEquals("Registration failed: Error: User already exists", controller.invalidLoginMessage.getText());
    }

    @Test
    void testCancelButtonOnAction_ChangesScene() throws Exception {
        // TODO: Use a JavaFX testing framework like TestFX to test UI transitions.
    }

    @Test
    void testOnPolylineHoverStyleChange() {
        // Act
        controller.onPolylineHover();

        // Assert
        assertEquals("-fx-stroke: #000ea8; -fx-stroke-width: 4px;", controller.cancelButton.getStyle());
    }

    @Test
    void testOnPolylineExitStyleChange() {
        // Act
        controller.onPolylineExit();

        // Assert
        assertEquals("-fx-stroke: #8589f1; -fx-stroke-width: 2px;", controller.cancelButton.getStyle());
    }
}

