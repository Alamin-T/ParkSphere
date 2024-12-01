package full.stack.parkspring.model;

import full.stack.parkspring.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the id field
    private Integer id;

    @Column(nullable = false, unique = true) // Ensure email is unique
    private String email;

    @Column(nullable = false, unique = true) // Ensure username is unique
    private String username;

    @Column(nullable = false) // Ensure password is not null
    private String password;

    @Enumerated(EnumType.STRING) // Store gender as a string in the DB
    @Column(nullable = false) // Make sure gender is not null
    private Gender gender;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // One user can have many vehicles
    private List<Vehicle> vehicles;

    // You don't need explicit getters, setters, constructors, or builder methods
    // because Lombok will generate them for you.
}
