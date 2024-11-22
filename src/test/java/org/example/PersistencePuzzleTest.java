package org.example;


import org.example.model.Person;
import org.example.store.PersistencePuzzle;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

/**
 * Persistence Puzzle
 *
 * We need to save and retrieve person object from java to RDBMS.
 * Let's Measure the fundamental approaches.
 *
 * Note: This is not about feature list of individual library / Framework.
 */
class PersistencePuzzleTest {

    @Test
    void test() throws SQLException {

        PersistencePuzzle persistencePuzzle = new PersistencePuzzle(getDataSource());

        // The Puzzle
        Person person = persistencePuzzle.save(new Person(null, "Hello"));

        System.out.println(person);

    }





















    private static PGSimpleDataSource getDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("postgres");
        ds.setUser("postgres");
        ds.setPassword("postgres123");
        return ds;
    }
}