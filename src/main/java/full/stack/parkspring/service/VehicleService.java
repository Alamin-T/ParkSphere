package full.stack.parkspring.service;

import full.stack.parkspring.config.UserSession;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.model.LicenseClass;
import full.stack.parkspring.model.PowerType;
import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.repository.UserRepository;
import full.stack.parkspring.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public void registerVehicle(Vehicle vehicle) {

        vehicleRepository.save(vehicle);

    }

}
