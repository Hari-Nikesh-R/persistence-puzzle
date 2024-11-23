package org.example.store;

import org.example.model.Person;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.Optional;


/**
 * 1. Only You, JDK and JDBC Driver (Postgres) + Little Etc + More Etc
 *
 *
 * 2. Use What I give in JAVA
 * 3. 3. You use this in Spring Boot, Quarkus or Anything. (Framework/Library Independent)
 */
public class PersistencePuzzle {

    private static final String INSERT_SQL = "INSERT INTO person(name) VALUES (?)";
    private static final String SELECT_SQL = "SELECT id,name from person where id=?";

    private final DataSource dataSource;

    public PersistencePuzzle(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Person save(Person person) throws SQLException {
        // Insert and Get Generated Person Id
        long generatedId = -1;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement(INSERT_SQL,
                             Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.name());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        }
        // Fetch Person from Generated Person Id
        return findById(generatedId).orElse(null);
    }

    Optional<Person> findById(Long id) throws SQLException {
        Person person = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection
                     .prepareStatement(SELECT_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                person = new Person(resultSet.getLong(1),
                        resultSet.getString(2));
            }
        }
        return Optional.ofNullable(person);
    }
}
