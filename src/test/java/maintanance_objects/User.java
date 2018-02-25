package maintanance_objects;

/**
 * Created by @v.matviichenko
 */
public class User {
    private String firstName;
    private String lastName;

    public User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreateBodyRequest() {
        return String.format("{\"email\":\"%s.%s@gmail.com\",\"firstName\":\"%s\",\"lastName\":\"%s\"}",
                this.firstName, this.lastName, this.firstName, this.lastName);
    }

}
