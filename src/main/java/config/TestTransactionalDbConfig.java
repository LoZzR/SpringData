package config;

import jdbctemplate.config.TestDataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = {"services"})
@Import(TestDataConfig.class)
public class TestTransactionalDbConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    @Primary
    @Bean
    public PlatformTransactionManager txManager(){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public PlatformTransactionManager simpleManager(){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
    /*@Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("db/schema.sql")
                .addScript("db/test-data.sql")
                .build();
        return db;
    }*/
    /* Setting the default transactionManager*/
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
}