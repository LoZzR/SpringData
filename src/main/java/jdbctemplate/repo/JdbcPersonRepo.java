package jdbctemplate.repo;

import entities.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import repo.IPersonRepo;
import util.DateProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcPersonRepo implements IPersonRepo {

    private RowMapper<Person> rowMapper = new PersonRowMapper();
    protected JdbcTemplate jdbcTemplate;

    public JdbcPersonRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<Person> findById(Long id) {
        var sql = "select ID, USERNAME, FIRSTNAME, LASTNAME,PASSWORD,HIRINGDATE from PERSON where ID= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql,
                rowMapper, id));
    }
    @Override
    public Set<Person> findAll() {
        var sql = "select ID, USERNAME, FIRSTNAME, LASTNAME,PASSWORD,HIRINGDATE from PERSON";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    /**
     * Maps a row returned from a query executed
     * on the PERSON table to a Person object.
     */
    private class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws
                SQLException {
            var id = rs.getLong("ID");
            var username = rs.getString("USERNAME");
            var firstname = rs.getString("FIRSTNAME");
            var lastname = rs.getString("LASTNAME");
            var password = rs.getString("PASSWORD");
            var hiringDate = rs.getString("HIRINGDATE");
            var person = new Person();
            person.setId(id);
            person.setUsername(username);
            person.setFirstName(firstname);
            person.setLastName(lastname);
            person.setPassword(password);
            if (hiringDate!= null && !"".equals(hiringDate)) person.setHiringDate(DateProcessor.toDate(hiringDate));
            return person;
        }
    }
}
