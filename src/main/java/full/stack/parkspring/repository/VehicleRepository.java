package full.stack.parkspring.repository;

import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.model.PowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> { // Change Integer to Long

    // Read operations
    Optional<Vehicle> findByPlate(String plate);

    // List vehicles by PowerType (still relevant)
    List<Vehicle> findByPowerType(PowerType powerType);

    // Delete operations
    void deleteById(Integer id); // Change Integer to Long if needed
    void deleteByPlate(String plate);

    // Existence checks
    boolean existsByPlate(String plate);
}
