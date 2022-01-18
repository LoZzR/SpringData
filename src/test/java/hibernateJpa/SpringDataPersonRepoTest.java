package hibernateJpa;

import hibernateJpa.config.SpringDataConfig;
import hibernateJpa.entities.Person;
import hibernateJpa.repo.SpringDataPersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataConfig.class)
public class SpringDataPersonRepoTest {

    @Autowired
    SpringDataPersonRepo personRepo;

    @BeforeEach
    void setUp(){
        assertNotNull(personRepo);
    }

    @Test
    void testFindByUsernamePositive(){
        Person person = new Person();
        person.setFirstName("Soufiane");
        person.setLastName("Ounida");

        Person newPerson = personRepo.save(person);


        assertNotNull(personRepo.findByFirstName(person.getFirstName()).get());

        assertEquals(1, personRepo.getCountPerson());
    }



}
