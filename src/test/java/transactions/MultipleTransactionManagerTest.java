package transactions;

import config.TestTransactionalDbConfig;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, JdbcConfig.class})
public class MultipleTransactionManagerTest {

    public static Logger logger = LoggerFactory.getLogger(MultipleTransactionManagerTest.class);

    @Autowired
    IPersonService personService;

    @Autowired
    ApplicationContext ctx;

    @Test
    void testFindByIdWithTwoTms() {
        DataSource sd = (DataSource) ctx.getBean(DataSource.class) ;
       assertEquals("John", personService.findById(1L).getFirstName());
    }

    @Test
    void testBeanTestExist(){
        assertNotNull(ctx.getBean(config.Test.class));
    }
}
