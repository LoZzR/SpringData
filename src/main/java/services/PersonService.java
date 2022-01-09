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
        return personRepo.findById(id).orElseThrow(()->new RuntimeException(String.format("Person with id : %s not found !",id)));
    }

    @Override
    public Set<Person> findAll() {
        return personRepo.findAll();
    }
}
