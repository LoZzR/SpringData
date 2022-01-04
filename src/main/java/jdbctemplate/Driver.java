package jdbctemplate;

import jdbctemplate.config.TestDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Driver {

    private static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String args []){
        logger.info("====================================>>> Application Start");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(TestDataConfig.class);


       for(String bean: ctx.getBeanDefinitionNames()){
           logger.info("========================> {}", bean);
       }
    }
}
