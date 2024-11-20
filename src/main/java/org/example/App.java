package org.example;

import org.example.model.Cinema;
import org.example.store.CinemaStore;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        DataManager dataManager = DataManager.getManager();
        CinemaStore cinemaStore = dataManager.getCinemaStore();
        cinemaStore.delete().execute(dataSource());
        cinemaStore.insert().values(new Cinema((short) 3, "new Movie", "Saravana")).execute(dataSource());
        List<Cinema> cinemas = cinemaStore.select().execute(dataSource());
        System.out.println(cinemas.stream().findFirst().get().directedBy());
        System.out.println(cinemaStore.select().count(dataSource()));
    }

    public static DataSource dataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource() ;
        ds.setServerName( "localhost" );
        ds.setDatabaseName( "moviedb" );
        ds.setUser( "moviedb" );
        ds.setPassword( "moviedb" );
        ds.setDatabaseName("moviedb");

        return ds;
    }
}