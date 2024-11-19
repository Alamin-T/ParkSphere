package full.stack.parkspring.repository;

import full.stack.parkspring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Read operations
    List<Booking> findByUser(User user);
    List<Booking> findBySpot(Spot spot);
    List<Booking> findByVehicle(Vehicle vehicle);
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByBookingStartBetween(LocalDateTime start, LocalDateTime end);
    List<Booking> findByBookingEndBetween(LocalDateTime start, LocalDateTime end);
    Optional<Booking> findByUserAndSpotAndStatus(User user, Spot spot, BookingStatus status);

    // Create/Update operations (already provided by save())
    <S extends Booking> S save(S booking);

    // Delete operations
    void deleteById(Integer id);
    void deleteByUser(User user);
    void deleteBySpot(Spot spot);

    // Existence checks
    boolean existsByUserAndSpotAndBookingEndAfter(User user, Spot spot, LocalDateTime now);
}
