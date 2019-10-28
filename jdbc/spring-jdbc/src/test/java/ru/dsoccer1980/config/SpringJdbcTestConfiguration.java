package ru.dsoccer1980.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.AuthorDaoJdbc;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.BookDaoJdbc;

@Configuration
public class SpringJdbcTestConfiguration {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema-test.sql")
                .build();
        return db;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate());
        return namedParameterJdbcTemplate;
    }

    @Bean
    public AuthorDao authorDao() {
        AuthorDao authorDao = new AuthorDaoJdbc(namedParameterJdbcTemplate());
        return authorDao;
    }

    @Bean
    public BookDao bookDao() {
        BookDao bookDao = new BookDaoJdbc(namedParameterJdbcTemplate(), authorDao());
        return bookDao;
    }

}

