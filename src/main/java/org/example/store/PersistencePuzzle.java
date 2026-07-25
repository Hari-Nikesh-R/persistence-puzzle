package org.example.store;

import org.example.model.Person;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

public class PersistencePuzzle {

    private static final String INSERT_SQL = "INSERT INTO person(name) VALUES (?)";
    private static final String SELECT_SQL = "SELECT id, name FROM person WHERE id = ?";

    private final JdbcClient jdbcClient;

    public PersistencePuzzle(final DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public PersistencePuzzle(final JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Person save(Person person) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(INSERT_SQL)
                .param(1, person.name())
                .update(keyHolder, "id");

        Number generatedKey = keyHolder.getKey();
        long generatedId = generatedKey != null ? generatedKey.longValue() : -1;

        return findById(generatedId).orElse(null);
    }

    Optional<Person> findById(Long id) throws SQLException {
        return jdbcClient.sql(SELECT_SQL)
                .param(1, id)
                .query((rs, rowNum) -> new Person(rs.getLong("id"), rs.getString("name")))
                .optional();
    }
}
