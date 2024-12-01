package full.stack.parkspring.frontend;

import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Register a new vehicle
    @PostMapping("/register")
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        if (vehicleRepository.existsByPlate(vehicle.getPlate())) {
            throw new IllegalArgumentException("Vehicle with this plate already exists.");
        }
        return vehicleRepository.save(vehicle);
    }

    // Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Find vehicle by plate
    @GetMapping("/{plate}")
    public Vehicle findVehicleByPlate(@PathVariable String plate) {
        return vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new IllegalArgumentException("No vehicle found with plate: " + plate));
    }

    // Delete a vehicle by plate
    @DeleteMapping("/{plate}")
    public void deleteVehicle(@PathVariable String plate) {
        if (!vehicleRepository.existsByPlate(plate)) {
            throw new IllegalArgumentException("Vehicle not found.");
        }
        vehicleRepository.deleteByPlate(plate);
    }
}
