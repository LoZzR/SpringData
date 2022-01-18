package hibernateJpa.repo;

import hibernateJpa.entities.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.DateProcessor;

import javax.sql.DataSource;

public class CustomizedPersonRepoImpl implements CustomizedPersonRepo{
    private JdbcTemplate jdbcTemplate;

    public CustomizedPersonRepoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public int getCountPerson() {
        String query = "SELECT COUNT(*) FROM PERSON";
        return this.jdbcTemplate.queryForObject(query, Integer.class);
    }

    private RowMapper<Person> rowMapper = (rs, i) -> {
        Person p = new Person();
        p.setId(rs.getLong("ID"));
        p.setFirstName(rs.getString("FIRSTNAME"));
        p.setLastName(rs.getString("LASTNAME"));
        p.setUsername(rs.getString("USERNAME"));
        //p.setHiringDate(DateProcessor.toDate(rs.getString("HIRINGDATE")));
        return p;
    };
}
