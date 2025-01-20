package com.bspicinini.controller.healthcheck;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {

    private DataSource dataSource;

    public DatabaseHealthCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public HealthCheckResponse call() {
        // Perform a health check (e.g., check database connection)
        boolean databaseIsUp = checkDatabaseConnection();
        if (databaseIsUp) {
            return HealthCheckResponse.up("Database connection is healthy");
        } else {
            return HealthCheckResponse.down("Database connection is down");
        }
    }

    private boolean checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}