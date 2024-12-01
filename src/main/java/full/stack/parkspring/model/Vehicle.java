package full.stack.parkspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data  // Lombok will generate getters, setters, toString(), equals(), and hashCode() methods
@NoArgsConstructor  // Lombok will generate a no-args constructor
@AllArgsConstructor // Lombok will generate an all-args constructor
@Builder  // Lombok will generate a builder pattern for this class

@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the id field
    private Integer id;  // Primary key, auto-generated

    private String plate;
    private String licenseNumber;

    @Enumerated(EnumType.STRING) // Ensure that PowerType is stored as a string in DB
    private PowerType powerType;

    @Enumerated(EnumType.STRING) // Assuming LicenseClass is an enum
    private LicenseClass licenseClass;

    private String color;

    private String model;

    @ManyToOne(fetch = FetchType.EAGER)  // Many vehicles can belong to one user
    private AppUser user;

    // You don't need explicit getters, setters, constructors, or builder methods
    // because Lombok will generate them for you.
}
