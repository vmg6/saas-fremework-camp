package helpers;

/**
 * Created by @v.matviichenko
 */
public class ContactObject {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public ContactObject() {

    }

    public ContactObject(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {

        return this.firstName + "." + this.lastName + "@gmail.com";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestBody() {
        return String.format("{\"email\":\"%s.%s@gmail.com\",\"firstName\":\"%s\",\"lastName\":\"%s\"}",
                this.firstName, this.lastName, this.firstName, this.lastName);
    }

}
