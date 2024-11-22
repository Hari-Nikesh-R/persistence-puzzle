package org.example;


import com.example.tables.Person;
import com.example.tables.records.PersonRecord;
import org.example.store.PersistencePuzzle;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
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

    private final PersistencePuzzle persistencePuzzle;
    PersistencePuzzleTest() {
        persistencePuzzle = new PersistencePuzzle();
    }

    @Test
    void test()  {
        DSLContext context = DSL.using(getDataSource(), SQLDialect.POSTGRES);
        PersonRecord person = context.newRecord(Person.PERSON);
        person.setName("Name");

        // The Puzzle
        PersonRecord personRecord = persistencePuzzle.save(person);

        System.out.println(personRecord);

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