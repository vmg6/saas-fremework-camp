package helpers;

/**
 * Created by @v.matviichenko
 */
public class ContactData {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public ContactData() {

    }

    public ContactData(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestBody() {
        return String.format("{\"email\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\"}",
                this.email, this.firstName, this.lastName);
    }

    public String getRequestBodyWithoutEmail() {
        return String.format("{\"firstName\":\"%s\",\"lastName\":\"%s\"}",
                this.firstName, this.lastName);
    }

    public String getRequestBodyWithoutFirstName() {
        return String.format("{\"lastName\":\"%s\", \"email\":\"%s\"}",
                this.lastName, this.email);
    }

    public String getRequestBodyWithoutLastName() {
        return String.format("{\"firstName\":\"%s\", \"email\":\"%s\"}",
                this.firstName, this.email);
    }

    public String getRequestWithEmptyBody() {
        return "{\"\"}";
    }

}
