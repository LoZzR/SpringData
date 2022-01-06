package jdbctemplate.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExtraJdbcPersonRepo extends JdbcPersonRepo {

    public ExtraJdbcPersonRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private class HTMLPersonRowCallbackHandler
            implements RowCallbackHandler {
        private PrintStream out;

        public HTMLPersonRowCallbackHandler(PrintStream out) {
            this.out = out;
        }

        @Override
        public void processRow(ResultSet rs)
                throws SQLException {
            out.print("<p>person ID: ".concat(rs.getLong("ID") + "")
                    .concat("</p></br>\n")
                    .concat("<p>username: ").concat(rs.getString("USERNAME"))
                    .concat("</p></br>"));
        }
    }

    public void htmlAllByName(String name) {
        String sql = "select * from PERSON where USERNAME= ?";
        jdbcTemplate.query(sql, new HTMLPersonRowCallbackHandler(System.out),
                name);
    }
}
