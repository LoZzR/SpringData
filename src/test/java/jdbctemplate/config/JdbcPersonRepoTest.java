package jdbctemplate.config;

import jdbctemplate.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import repo.IPersonRepo;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {TestDataConfig.class, JdbcConfig.class})
class JdbcPersonRepoTest {
    static final Long PERSON_ID = 1L;

    @Autowired
    //@Qualifier("extraJdbcPersonRepo")
    IPersonRepo personRepo;
    @BeforeEach
    void setUp(){
        assertNotNull(personRepo);
    }

    @Test
    void testFindByIdPositive(){
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("John", p.getUsername()),
                Assertions:: fail
        );
    }
    @Test
    void testFindByIdNegative(){
        assertThrows(EmptyResultDataAccessException.class,
                () -> personRepo.findById(99L));
    }
    @Test
    void testFindAll(){
        Set<Person> personSet = personRepo.findAll();
        assertNotNull(personSet);
        assertEquals(4, personSet.size());
    }
    @Test
    void htmlAllByName(){
        personRepo.htmlAllByName("John");
    }
}
