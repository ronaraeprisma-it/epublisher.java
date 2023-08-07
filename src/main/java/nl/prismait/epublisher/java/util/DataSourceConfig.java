package nl.prismait.epublisher.java.util;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        //change this to get from application.properties
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/epublisherdb")
                .username("epublisher")
                .password("password")
                .build();
    }
}
