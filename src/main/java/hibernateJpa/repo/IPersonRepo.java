package hibernateJpa.repo;


import hibernateJpa.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    Person addPerson(Person person);
    Long getNextId();
    Set<Person> findAll();
    default Optional<Person> findByCompleteName(String firstName, String
            lastName) {
        return Optional.empty();
    }
    default List<Person> findAllByLastName(String lastName) {
        return new ArrayList<>();
    }
    default void htmlAllByName(String name) {}

}
