package full.stack.parkspring.repository;

import full.stack.parkspring.model.Vehicle;
import full.stack.parkspring.model.PowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Read operations
    Optional<Vehicle> findByPlate(String plate);


    // Find vehicles by userId
    List<Vehicle> findByUserId(Long userId); // <-- Add this method

    // Delete operations
    void deleteById(Long id);
    void deleteByPlate(String plate);

    // Existence checks
    boolean existsByPlate(String plate);
}
