package full.stack.parkspring.frontend;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth; // You may use LocalDate if you prefer
    private String gender;
    private String password;

    public User(String firstName, String lastName, String email, String dateOfBirth, String gender, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getPassword() { return password; }
}
