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


    LicenseClass(String displayName) {
        this.displayName = displayName;
    }

    public static LicenseClass fromDisplayName(String displayName) {
        for (LicenseClass licenseClass : LicenseClass.values()) {
            if (licenseClass.getDisplayName().equalsIgnoreCase(displayName)) {
                return licenseClass;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}
