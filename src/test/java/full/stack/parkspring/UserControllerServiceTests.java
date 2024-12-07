package full.stack.parkspring;

import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.repository.UserRepository;
import full.stack.parkspring.frontend.UserControllerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UserControllerServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserControllerService userControllerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setUsername("testuser");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);

        // Act
        ResponseEntity<String> response = userControllerService.registerUser(user);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully!", response.getBody());

        verify(userRepository).save(user);
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        // Arrange
        AppUser user = new AppUser();
        user.setEmail("existing@example.com");
        user.setUsername("existinguser");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<String> response = userControllerService.registerUser(user);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Email already in use.", response.getBody());

        verify(userRepository, never()).save(user);
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        AppUser user = new AppUser();
        user.setEmail("new@example.com");
        user.setUsername("existingusername");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        // Act
        ResponseEntity<String> response = userControllerService.registerUser(user);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Username already in use.", response.getBody());

        verify(userRepository, never()).save(user);
    }
}
