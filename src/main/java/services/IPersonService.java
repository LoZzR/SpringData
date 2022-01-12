package services;

import jdbctemplate.entities.Person;
import java.util.Set;

public interface IPersonService {

    Person findById(Long id);
    Person addNewPerson(Person person);
    Set<Person> findAll();
}
