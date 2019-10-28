package ru.dsoccer1980.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.AuthorDaoJdbc;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.BookDaoJdbc;

@Configuration
public class SpringJdbcConfiguration {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //MySQL database we are using
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/TestDB");//change url
//        dataSource.setUsername("userid");//change userid
//        dataSource.setPassword("password");//change pwd

        //H2 database

//        dataSource.setDriverClassName("org.h2.Driver");
//       // dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
//        dataSource.setUrl("jdbc:h2:mem:testdb");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//        return dataSource;

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/proddb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;

//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = builder
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("schema.sql")
//                .addScript("data.sql")
//                .build();
//        return db;

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
    public AuthorDao authorDao(){
        AuthorDao authorDao = new AuthorDaoJdbc(namedParameterJdbcTemplate());
        return authorDao;
    }

    @Bean
    public BookDao bookDao(){
        BookDao bookDao = new BookDaoJdbc(namedParameterJdbcTemplate(), authorDao());
        return bookDao;
    }

}

