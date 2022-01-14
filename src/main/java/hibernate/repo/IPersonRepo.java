package hibernate.repo;


import hibernate.entities.Person;

import java.util.Optional;
import java.util.Set;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    Person addPerson(Person person);
    Long getNextId();
    Set<Person> findAll();
    default void htmlAllByName(String name) {}

}
