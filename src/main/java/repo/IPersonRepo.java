package repo;


import jdbctemplate.entities.Person;

import java.util.*;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    Person addPerson(Person person);
    Long getNextId();
    Set<Person> findAll();
    default void htmlAllByName(String name) {}

}
