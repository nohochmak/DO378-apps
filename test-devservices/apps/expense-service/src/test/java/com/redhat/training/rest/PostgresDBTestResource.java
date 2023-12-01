package com.redhat.training.rest;

import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager;

import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer; 
import org.testcontainers.utility.DockerImageName;

public class PostgresDBTestResource implements QuarkusTestResourceConfigurableLifecycleManager<WithPostgresDB> {

    private static final DockerImageName imageName = DockerImageName
            .parse( "docker.io/library/postgres:14" ) 
            .asCompatibleSubstituteFor( "postgres" );

    private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>( imageName );
    private String name; 
    private String username; 
    private String password;



    @Override 
    public void init( WithPostgresDB params ) {

        username = params.username();
        password = params.password();
        name = params.name(); 
    }

    @Override public Map<String, String> start() {

        DATABASE.withDatabaseName( name ) 
            .withUsername( username ) 
            .withPassword( password ) 
            .start();
        
        return Map.of( "quarkus.datasource.username", username, 
                       "quarkus.datasource.password", password, 
                       "quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl()
                    );
        }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
