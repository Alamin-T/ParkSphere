package full.stack.parkspring.frontend;


import full.stack.parkspring.model.AppUser;
import full.stack.parkspring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserControllerService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser user) {
        // Check if the email or username already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already in use.");
        }

        // Save the user to the database

        userRepository.save(user);


        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserDetails(@PathVariable String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
