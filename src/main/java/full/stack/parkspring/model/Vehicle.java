package full.stack.parkspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String plate;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @Enumerated(EnumType.STRING)
    private PowerType powerType;
}
