package jdbctemplate.repo;

import jdbctemplate.entities.Detective;
import jdbctemplate.entities.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import util.DateProcessor;
import util.EmploymentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ExtraJdbcDetectiveRepo extends JdbcDetectiveRepo {

    public ExtraJdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Detective> findByIdWithPersonDetails(Long id) {
        String sql = "select d.ID id," +
                " p.ID pid, " +
                " p.USERNAME un," +
                " p.FIRSTNAME fn, " +
                " p.LASTNAME ln, " +
                " p.HIRINGDATE hd," +
                " d.BADGE_NUMBER bno," +
                " d.ARMED armed," +
                " d.STATUS status" +
                " from DETECTIVE d, PERSON p " +
                "where d.PERSON_ID=p.ID and d.ID=" + id;
        return Optional.of(jdbcTemplate.query(sql, new DetectiveExtractor()));
    }
    private class DetectiveExtractor implements
            ResultSetExtractor<Detective> {
        @Override
        public Detective extractData(ResultSet rs) throws SQLException {
            Detective detective = null;
            while (rs.next()) {
                if (detective == null) {
                    detective = new Detective();
// set internal entity identifier (primary key)
                    detective.setId(rs.getLong("id"));
                    detective.setBadgeNumber(rs.getString("bno"));
                    detective.setArmed(rs.getBoolean("armed"));
                    detective.setStatus(
                            EmploymentStatus.valueOf(rs.getString("status")));
                }
                Person p = new Person();
                p.setId(rs.getLong("pid"));
                p.setUsername(rs.getString("un"));
                p.setFirstName(rs.getString("fn"));
                p.setLastName(rs.getString("ln"));
                if(rs.getString("hd") != null && !"".equals(rs.getString("hd")))
                    p.setHiringDate(DateProcessor.toDate(rs.getString("hd")));
                detective.setPerson(p);
            }
            return detective;
        }
    }
}