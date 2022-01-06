package jdbctemplate.repo;

import entities.Detective;
import entities.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import repo.IDetectiveRepo;
import util.EmploymentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcDetectiveRepo implements IDetectiveRepo {
    private RowMapper<Detective> rowMapper = new JdbcDetectiveRepo.DetectiveRowMapper();
    protected JdbcTemplate jdbcTemplate;

    public JdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<Detective> findById(Long id) {
        var sql = "select d.ID, d.BADGE_NUMBER, d.ARMED, d.STATUS,d.PERSON_ID, " +
                "p.USERNAME, p.FIRSTNAME, p.LASTNAME, p.HIRINGDATE "+
                "from DETECTIVE d, PERSON p where d.ID= ? and d.PERSON_ID=p.ID";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        var sql = "select ID, BADGE_NUMBER, ARMED, STATUS,PERSON_ID from DETECTIVE where BADGE_NUMBER= ?";
        var detective = jdbcTemplate.queryForObject(sql, rowMapper, badgeNumber);
        return detective == null ? Optional.empty() : Optional.of(detective);
    }

    class DetectiveRowMapper implements RowMapper<Detective> {
        @Override
        public Detective mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong("ID");
            var badgeNumber = rs.getString("BADGE_NUMBER");
            var armed = rs.getBoolean("ARMED");
            var status = rs.getString("STATUS");
            var personId = rs.getLong("PERSON_ID");

            var person = new Person();
            person.setId(personId);
            person.setUsername(rs.getString("USERNAME"));
            person.setFirstName(rs.getString("FIRSTNAME"));
            person.setLastName(rs.getString("LASTNAME"));
            person.setHiringDate(rs.getTimestamp("HIRINGDATE") != null ? rs.getTimestamp("HIRINGDATE").toLocalDateTime() : null);

            var detective = new Detective();
            detective.setId(id);
            detective.setPerson(person);
            detective.setBadgeNumber(badgeNumber);
            detective.setArmed(armed);
            detective.setStatus(EmploymentStatus.valueOf(status));

            return detective;
        }
    }
}
