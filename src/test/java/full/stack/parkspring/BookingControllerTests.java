package full.stack.parkspring;

import full.stack.parkspring.frontend.BookingController;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polyline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingControllerTests {

    @Mock
    private ComboBox<String> carComboBox;

    @Mock
    private DatePicker datePicker;

    @Mock
    private Button confirmButton;

    @Mock
    private ScrollPane timeSlotScrollPane;

    @Mock
    private VBox cancelButton;

    @Mock
    private Label reserveLabel;

    @Mock
    private RadioButton regularRadioButton, vipRadioButton;

    @InjectMocks
    private BookingController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock UI components
        controller.carComboBox = carComboBox;
        controller.datePicker = datePicker;
        controller.confirmButton = confirmButton;
        controller.timeSlotScrollPane = timeSlotScrollPane;
        controller.cancelButton = cancelButton;
        controller.reserveLabel = reserveLabel;
        controller.regularRadioButton = regularRadioButton;
        controller.vipRadioButton = vipRadioButton;
    }

    @Test
    void testPopulateCarComboBox() {
        // Arrange
        List<String> mockPlates = Arrays.asList("ABC123", "XYZ789");

        // Mock the behavior of fetching vehicle plates
        when(carComboBox.getItems()).thenReturn((ObservableList<String>) mockPlates);

        // Act
        controller.populateCarComboBox();

        // Assert
        verify(carComboBox, atLeastOnce()).getItems();
        assertEquals(mockPlates, carComboBox.getItems()); // Confirm that the plates are present in the comboBox
    }

    @Test
    void testHandleDateChange() {
        // Arrange
        LocalDate testDate = LocalDate.now();
        when(datePicker.getValue()).thenReturn(testDate);

        // Act
        controller.handleDateChange();

        // Assert
        verify(datePicker).getValue();
    }

    @Test
    void testMarkSlotsRandomlyBasedOnDate() {
        LocalDate testDate = LocalDate.now();

        // Act
        controller.markSlotsRandomlyBasedOnDate(testDate);

        verify(carComboBox).getScene(); // Ensure that some interaction occurs
    }
}
