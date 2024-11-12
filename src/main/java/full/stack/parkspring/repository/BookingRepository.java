package full.stack.parkspring.repository;

import full.stack.parkspring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);
    List<Booking> findBySpot(Spot spot);
    List<Booking> findByVehicle(Vehicle vehicle);
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByBookingStartBetween(LocalDateTime start, LocalDateTime end);
    List<Booking> findByBookingEndBetween(LocalDateTime start, LocalDateTime end);
    Optional<Booking> findByUserAndSpotAndStatus(User user, Spot spot, BookingStatus status);
    boolean existsByUserAndSpotAndBookingEndAfter(User user, Spot spot, LocalDateTime now);
}
