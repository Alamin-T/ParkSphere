package full.stack.parkspring.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class UserHomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Customize welcome message
        welcomeLabel.setText("Welcome, [Username]!");
        // Load user-specific profile image, etc.
    }


}
