package hibernate;


import hibernate.config.HibernateConfig;
import hibernate.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import hibernate.repo.IPersonRepo;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringJUnitConfig(classes = {HibernateConfig.class})
public class HibernatePersonRepoTest {


    @Autowired
    IPersonRepo personRepo;

    @BeforeEach
    void setUp(){
        assertNotNull(personRepo);
    }

    @Test
    void testFindAllPositive(){
        assertEquals(0, personRepo.findAll().size());
    }

    @Test
    void testAddPersonPositive(){

        Person p = new Person();
        p.setFirstName("Soufiane");
        p.setLastName("Ounida");
        assertNotNull(personRepo.addPerson(p));
        //assertEquals(1, personRepo.findAll().size());
    }
}
