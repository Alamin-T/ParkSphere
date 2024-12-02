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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)  // Ensure plate number is unique
    private String plate;

    @Column(nullable = false)  // Ensure license number is not null
    private String licenseNumber;

    @Enumerated(EnumType.STRING) // Ensure that PowerType is stored as a string in DB
    @Column(nullable = false)
    private PowerType powerType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LicenseClass licenseClass;

    private String color;

    private String model;

    @ManyToOne(fetch = FetchType.EAGER)  // Many vehicles can belong to one user
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to AppUser
    private AppUser user;


}
