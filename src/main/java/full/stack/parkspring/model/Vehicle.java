package full.stack.parkspring.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    private String licenseNumber;  // Added licenseNumber field

    @Enumerated(EnumType.STRING)
    private PowerType powerType;

    @Enumerated(EnumType.STRING)
    private LicenseClass licenseClass;  // Adding the LicenseClass enum

    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id") // Adjust nullability as required
    private AppUser user;
}
