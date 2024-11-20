package full.stack.parkspring.repository;

import full.stack.parkspring.model.Gender;
import full.stack.parkspring.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    // Read operations
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmailAndUsername(String email, String username);
    List<AppUser> findAllByGender(Gender gender); // Example: Custom filter by gender.

    // Create/Update operations (already provided by save())
    <S extends AppUser> S save(S user);

    // Delete operations
    void deleteById(Long id);
    void deleteByEmail(String email);

    // Existence checks
   /* boolean existsByEmail(String email);  */
    boolean existsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM AppUser u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

}
