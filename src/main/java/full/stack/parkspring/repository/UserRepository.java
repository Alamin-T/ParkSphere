package full.stack.parkspring.repository;

import full.stack.parkspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Read operations
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndUsername(String email, String username);
    List<User> findAllByGender(String gender); // Example: Custom filter by gender.

    // Create/Update operations (already provided by save())
    <S extends User> S save(S user);

    // Delete operations
    void deleteById(Long id);
    void deleteByEmail(String email);

    // Existence checks
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
