package repo;


import entities.Person;

import java.util.*;

public interface IPersonRepo {
    Optional<Person> findById(Long id);
    Set<Person> findAll();

}
