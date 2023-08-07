package nl.prismait;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/testing")
public class TestDB {

    private static final Logger logger = LoggerFactory.getLogger(TestDB.class);

    private final JdbcTemplate jdbcTemplate;

    public TestDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String testDBConnection() {
        try {
            String query = "SELECT COUNT (*) FROM zorggroep_almere.broadcast";
            String resultSet = jdbcTemplate.queryForObject(query, String.class);
          //  logger.info("Query result: "+ resultSet);

            return "Connection is SUCCESSFUL " + "Query Result: " + resultSet;
        }
        catch (Exception ex) {
            logger.error("Error executing query: ", ex.getMessage());
            return "Connection FAILED.";
        }
    }
}
