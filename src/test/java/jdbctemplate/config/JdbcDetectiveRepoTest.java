package jdbctemplate.config;

import jdbctemplate.entities.Detective;
import jdbctemplate.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import repo.IDetectiveRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {TestDataConfig.class, JdbcConfig.class})
public class JdbcDetectiveRepoTest {

    private static Logger logger = LoggerFactory.getLogger(JdbcDetectiveRepoTest.class);
    static final Long DETECTIVE_ID = 1L;

    @Autowired
    @Qualifier("extraJdbcDetectiveRepo")
    IDetectiveRepo detectiveRepo;

    @BeforeEach
    void setUp(){
        assertNotNull(detectiveRepo);
    }

    @Test
    void testFindByIdPositive(){
        detectiveRepo.findById(DETECTIVE_ID).ifPresentOrElse(
                d -> assertEquals("LD112233", d.getBadgeNumber()),
                Assertions:: fail
        );
    }
    @Test
    void testFindByIdNegative(){
        assertThrows(EmptyResultDataAccessException.class,
                () -> detectiveRepo.findById(99L));
    }

    @Test
    void testFindByIdWithDetails(){
        Optional<Detective> detective = detectiveRepo
                .findByIdWithPersonDetails(DETECTIVE_ID);detective.ifPresentOrElse(
                d -> assertNotNull(d.getPerson()),
                Assertions:: fail
        );
        logger.info("Result: {}", detective);
    }
}
