package jdbctemplate.entities;

import util.EmploymentStatus;
import java.util.Objects;

public class Detective{
    private Long id;
    private Person person;
    private String badgeNumber;
    private Boolean armed;
    private EmploymentStatus status;

    public Detective() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var detective = (Detective) o;
        return Objects.equals(person, detective.person) &&
                Objects.equals(badgeNumber, detective.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber);
    }

    @Override
    public String toString() {
        return String.format("Detective\n\t[person='%s', badgeNumber='%s',  armed='%s', status='%s']",
                person.toString(), badgeNumber, armed, status);
    }
}
