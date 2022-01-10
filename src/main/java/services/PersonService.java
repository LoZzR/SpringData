package services;

import entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.IPersonRepo;

import java.util.Set;

@Service
public class PersonService implements IPersonService{

    @Autowired
    private IPersonRepo personRepo;

    public PersonService(IPersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Transactional(transactionManager = "simpleManager", readOnly = true)
    @Override
    public Person findById(Long id) {
        if(id%2 == 1) throw new RuntimeException(String.format("id %s not allowed !", id));
        return personRepo.findById(id).orElseThrow(()->new RuntimeException(String.format("Person with id : %s not found !",id)));
    }

    @Override
    @Transactional
    public Person addNewPerson(Person person) {
        Long id = this.personRepo.getNextId();
        person.setId(id);
        this.personRepo.addPerson(person);
        Set<Person> persons = this.findAll();
        return this.findById(id);
    }

    @Override
    public Set<Person> findAll() {
        return personRepo.findAll();
    }


}
