package transactions;

import config.TestTransactionalDbConfig;
import jdbctemplate.entities.Person;
import jdbctemplate.config.JdbcConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import services.IPersonService;
import util.DateProcessor;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, JdbcConfig.class})
public class MultipleTransactionManagerTest {

    public static Logger logger = LoggerFactory.getLogger(MultipleTransactionManagerTest.class);

    @Autowired
    IPersonService personService;

    @Test
    void testFindByIdWithTwoTms() {
        Person person = personService.findById(1L);
        assertEquals("John", person.getUsername());
    }


    @Test
    void testFindAll() {
        Set<Person> persons = personService.findAll();
        assertEquals(4, persons.size());
    }

    @Test
    void testAddNewPerson(){
        Person person = new Person("Zack10", "Zakariae", "EL HICHEM", "test123", DateProcessor.toDate("2021-03-01 08:00:00"));
        Person newPerson = personService.addNewPerson(person);
        assertNotNull(newPerson);
        assertEquals(5, newPerson.getId());
    }

    @Test
    void testAddNewPersonFailed(){
        Person person = new Person("OussMess", "Oussama", "Messaoudi", "test123", DateProcessor.toDate("2021-03-01 08:00:00"));
        assertThrows(RuntimeException.class, ()-> personService.addNewPerson(person));
        //Initial should not be impacted coz we're using @Transactional
        assertEquals(4, personService.findAll().size());
    }
}
