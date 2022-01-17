package hibernateJpa;

import hibernateJpa.config.JpaConfig;
import hibernateJpa.entities.Person;
import hibernateJpa.repo.IPersonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaConfig.class)
@Transactional
public class JpaPersonRepoTest {

    @Autowired
    private IPersonRepo personRepo;

    @BeforeEach
    void setUp(){
        assertNotNull(personRepo);
    }

    @Test
    void testAddPersonPositive(){
        Person p = new Person();
        p.setFirstName("Soufiane");
        p.setLastName("Ounida");

        personRepo.addPerson(p);
        assertEquals(1, personRepo.findAll().size());
    }
}
