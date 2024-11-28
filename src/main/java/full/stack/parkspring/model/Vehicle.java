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

    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    private PowerType powerType;

    @Enumerated(EnumType.STRING)
    private LicenseClass licenseClass;

    private String color;

    //@ManyToOne
   // @JoinColumn(name = "user_id")
   // private AppUser user;
}
