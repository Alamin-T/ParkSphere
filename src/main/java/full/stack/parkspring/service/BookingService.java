package full.stack.parkspring.service;

import full.stack.parkspring.model.Booking;
import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.repository.BookingRepository;
import full.stack.parkspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(Booking booking, String username) {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        booking.setUser(appUser);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUsername(String username) {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(appUser);
    }
}
