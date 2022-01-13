package hibernate.entities;

import org.springframework.format.annotation.DateTimeFormat;
import util.DateProcessor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Person extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false)
    private String password;

    @NotNull
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    @Column(nullable = false)
    private LocalDateTime hiringDate;

    @Transient
    private String newPassword;

    public Person() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(hiringDate.toLocalDate(), person.hiringDate.toLocalDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, hiringDate.toLocalDate());
    }

    @Override
    public String toString() {
        return String.format("Person[username='%s', firstName='%s', lastName='%s', hiringDate='%s']\n",
                username, firstName, lastName, hiringDate == null? "" : hiringDate.toString());

    }
}
