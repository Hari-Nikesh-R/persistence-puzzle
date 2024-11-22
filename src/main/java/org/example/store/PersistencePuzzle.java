package org.example.store;


import com.example.tables.Person;
import com.example.tables.records.PersonRecord;
import org.jooq.DSLContext;

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
 * 3. You use this in Only Spring Boot,  (Framework or library Dependent)
 */
public class PersistencePuzzle {

    public PersonRecord save(PersonRecord person) {
        // Insert and Get Generated Person Id
        person.store();

        // Fetch Person from Generated Person Id
        return person;
    }
}
