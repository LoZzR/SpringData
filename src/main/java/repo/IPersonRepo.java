package repo;


import entities.Person;

import java.util.*;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    void addPerson(Person person);
    Long getNextId();
    Set<Person> findAll();
    default void htmlAllByName(String name) {}

}
