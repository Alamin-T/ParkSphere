package full.stack.parkspring.frontend;

import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.repository.VehicleRepository;
import full.stack.parkspring.config.UserSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingControllerService {

    private final VehicleRepository vehicleRepository;

    public BookingControllerService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehiclesForUser(long userId) {
        // Assuming you have a repository or DAO layer
        return vehicleRepository.findByUserId(userId);
    }
}
