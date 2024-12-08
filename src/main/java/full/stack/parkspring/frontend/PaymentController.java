package full.stack.parkspring.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class PaymentController {

    @FXML
    private TextField cardholderNameField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private ComboBox<String> cardTypeComboBox;

    @FXML
    private TextField expirationMonthField;

    @FXML
    private TextField expirationYearField;

    @FXML
    private PasswordField cvvField;

    @FXML
    private TextArea billingAddressField;

    @FXML
    public Polyline cancelButton;

    @FXML
    public void initialize() {
        // Handle card number input and formatting
        cardNumberField.addEventFilter(KeyEvent.KEY_TYPED, this::formatCardNumber);
        cardNumberField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleCardNumberNavigation);
    }

    private void formatCardNumber(KeyEvent event) {
        String input = event.getCharacter();
        String currentText = cardNumberField.getText().replaceAll("\\s", "");  // Remove spaces for processing

        // Block non-numeric input and stop when the length reaches 16 digits
        if (!input.matches("\\d") || currentText.length() >= 16) {
            event.consume();
            return;
        }

        // If input is a digit, add it and format the text
        currentText += input;
        String formattedText = formatWithSpaces(currentText);
        cardNumberField.setText(formattedText);
        cardNumberField.positionCaret(formattedText.length());  // Place caret at the end after formatting
        event.consume();
    }

    private void handleCardNumberNavigation(KeyEvent event) {

        // Handle backspace to work past the spaces
        if (event.getCode().toString().equals("BACK_SPACE")) {
            String text = cardNumberField.getText().replaceAll("\\s", "");  // Remove spaces for handling
            if (cardNumberField.getCaretPosition() > 0 && !text.isEmpty()) {
                text = text.substring(0, text.length() - 1);  // Remove the last character
                String formattedText = formatWithSpaces(text);
                cardNumberField.setText(formattedText);
                cardNumberField.positionCaret(formattedText.length());
            }
            event.consume();
        }
    }

    private String formatWithSpaces(String cardNumber) {
        // Format the card number with spaces every 4 digits
        return cardNumber.replaceAll(".{4}(?!$)", "$0 ");
    }

    @FXML
    public void handleSavePaymentMethod() {
        String cardholderName = cardholderNameField.getText();
        String cardNumber = cardNumberField.getText().replaceAll("\\s+", "");  // Remove spaces from card number
        String cardType = cardTypeComboBox.getValue();
        String expirationMonth = expirationMonthField.getText();
        String expirationYear = expirationYearField.getText();
        String cvv = cvvField.getText();
        String billingAddress = billingAddressField.getText();

        if (validateInput(cardholderName, cardNumber, cardType, expirationMonth, expirationYear, cvv)) {
            // Handle the saving of payment information
            showAlert("Payment Method Added", "Your payment method has been successfully added.");
            cardholderNameField.clear();
            cardNumberField.clear();
            cardTypeComboBox.getSelectionModel().clearSelection();
            expirationMonthField.clear();
            expirationYearField.clear();
            cvvField.clear();
            billingAddressField.clear();
        } else {
            showAlert("Invalid Input", "Please make sure all fields are correctly filled out.");
        }
    }

    private boolean validateInput(String cardholderName, String cardNumber, String cardType, String expirationMonth, String expirationYear, String cvv) {
        if (cardholderName.isEmpty() || cardNumber.isEmpty() || cardType == null || expirationMonth.isEmpty() ||
                expirationYear.isEmpty() || cvv.isEmpty()) {
            return false;
        }
        return cardNumber.length() == 16 && expirationMonth.length() == 2 && expirationYear.length() == 4 && cvv.length() == 3;
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
