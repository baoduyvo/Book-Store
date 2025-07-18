package org.voduybao.media.dao.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ConnectionChecker {

    @Autowired
    private DatabaseClient databaseClient;

    @PostConstruct
    public void checkConnection() {
        databaseClient.sql("SELECT 1")
                .fetch()
                .first()
                .doOnSuccess(result -> System.out.println("✅ Connected to database successfully! Result: " + result))
                .doOnError(error -> System.err.println("❌ Failed to connect to database: " + error.getMessage()))
                .subscribe();
    }
}