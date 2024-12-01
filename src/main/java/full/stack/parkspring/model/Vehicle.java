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

    @Column(nullable = false, unique = true)  // Ensure plate number is unique
    private String plate;

    @Column(nullable = false)  // Ensure license number is not null
    private String licenseNumber;

    @Enumerated(EnumType.STRING) // Ensure that PowerType is stored as a string in DB
    @Column(nullable = false)  // Make sure powerType is not null
    private PowerType powerType;

    @Enumerated(EnumType.STRING) // Ensure that LicenseClass is stored as a string in DB
    @Column(nullable = false)  // Make sure licenseClass is not null
    private LicenseClass licenseClass;

    private String color;  // Optional field

    private String model;  // Optional field

    @ManyToOne(fetch = FetchType.EAGER)  // Many vehicles can belong to one user
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to AppUser
    private AppUser user;

    // Lombok will generate getters, setters, constructors, and builder methods.
}
