package full.stack.parkspring.model;

import lombok.Getter;

@Getter
public enum LicenseClass {
    PRIVATE("Private"),
    PUBLIC_TAXI("Public/Taxi"),
    COMMERCIAL("Commercial"),
    RENTAL("Rental"),
    DIPLOMATIC("Diplomatic"),
    EMERGENCY_POLICE("Emergency/Police");

    private final String displayName;

    // Constructor to associate the display name with the enum value
    LicenseClass(String displayName) {
        this.displayName = displayName;
    }

    // Static method to get enum from the display name
    public static LicenseClass fromDisplayName(String displayName) {
        for (LicenseClass licenseClass : LicenseClass.values()) {
            if (licenseClass.getDisplayName().equalsIgnoreCase(displayName)) {
                return licenseClass;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}
