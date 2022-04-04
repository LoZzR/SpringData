package services;

import exceptions.MailSendingException;
import jdbctemplate.entities.Person;
import java.util.Set;

public interface IPersonService {

    Person findById(Long id);
    Person addNewPerson(Person person) throws MailSendingException;
    Set<Person> findAll();
}
