package transactions;

import config.TestTransactionalDbConfig;
import entities.Person;
import jdbctemplate.config.JdbcConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import services.IPersonService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
