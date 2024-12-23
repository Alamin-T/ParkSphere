package full.stack.parkspring.repository;

import full.stack.parkspring.model.Spot;
import full.stack.parkspring.model.VehicleType;
import full.stack.parkspring.model.PowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {
    // Read operations
    Optional<Spot> findBySpotId(String spotId);
    List<Spot> findByVehicleType(VehicleType vehicleType);
    List<Spot> findByPowerType(PowerType powerType);
    List<Spot> findByVehicleTypeAndPowerType(VehicleType vehicleType, PowerType powerType);

    // Create/Update operations (already provided by save())
    <S extends Spot> S save(S spot);

    // Delete operations
    void deleteById(Integer id);
    void deleteBySpotId(String spotId);

    // Existence checks
    boolean existsBySpotId(String spotId);
}
