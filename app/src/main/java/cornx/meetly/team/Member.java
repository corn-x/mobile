package cornx.meetly.team;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class Member {
    private String firstName;
    private String lastName;
    private String mail;

    public Member(String mail, String lastName, String firstName) {
        this.mail = mail;
        this.lastName = lastName;
        this.firstName = firstName;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + mail;
    }
}
