package full.stack.parkspring.model;

import java.util.Arrays;

public enum PowerType {
    GASOLINE("Petrol / Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private final String displayName;

    PowerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PowerType fromDisplayName(String displayName) {
        return Arrays.stream(PowerType.values())
                .filter(type -> type.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid PowerType display name: " + displayName));
    }
}
