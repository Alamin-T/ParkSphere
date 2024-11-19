package full.stack.parkspring.repository;

import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.model.VehicleType;
import full.stack.parkspring.model.PowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    // Read operations
    Optional<Vehicle> findByPlate(String plate);
    List<Vehicle> findByVehicleType(VehicleType vehicleType);
    List<Vehicle> findByPowerType(PowerType powerType);
    List<Vehicle> findByVehicleTypeAndPowerType(VehicleType vehicleType, PowerType powerType);

    // Create/Update operations (already provided by save())
    <S extends Vehicle> S save(S vehicle);

    // Delete operations
    void deleteById(Integer id);
    void deleteByPlate(String plate);

    // Existence checks
    boolean existsByPlate(String plate);
}
