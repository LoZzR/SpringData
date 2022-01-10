package repo;


import entities.Person;

import java.util.*;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    Optional<Person> addPerson(Person person);
    Set<Person> findAll();
    default void htmlAllByName(String name) {}

}
