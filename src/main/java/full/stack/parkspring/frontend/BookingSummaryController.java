package full.stack.parkspring.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookingSummaryController {

    @FXML
    private Label carLabel;

    @FXML
    private Label parkingTypeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeSlotLabel;

    public void setTimeSlot(String timeSlot) {
        timeSlotLabel.setText(timeSlot);
    }

    public void setSummaryDetails(String car, String parkingType, String date, String timeSlot) {
        carLabel.setText(car);
        parkingTypeLabel.setText(parkingType);
        dateLabel.setText(date);
        timeSlotLabel.setText(timeSlot);
    }
}
