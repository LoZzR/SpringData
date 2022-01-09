package jdbctemplate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"jdbctemplate.repo"})
public class JdbcConfig {
}
