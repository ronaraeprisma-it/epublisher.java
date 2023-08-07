package nl.prismait.hibernate;

import nl.prismait.TestDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/epublisherdb";
        String username = "epublisher";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Connection successful
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            // Connection failed
            e.printStackTrace();
        }

    }

}
