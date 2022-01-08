package services;

import entities.Person;
import java.util.Set;

public interface IPersonService {

    Person findById(Long id);
    Set<Person> findAll();
}
