package com.legacy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static HikariDataSource dataSource;

    // El bloque static asegura que el pool se crea una sola vez cuando arranca la app
    static {
        try {
            Properties props = new Properties();
            try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("No se encontró config.properties. Usa config.properties.example como base.");
                }
                props.load(input);
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.usuario"));
            config.setPassword(props.getProperty("db.password"));

            // Configuraciones pro del pool
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000); // 30 segundos

            dataSource = new HikariDataSource(config);
            logger.info("HikariCP Connection Pool inicializado correctamente.");
        } catch (Exception e) {
            logger.error("Fallo crítico inicializando la base de datos", e);
            throw new RuntimeException(e);
        }
    }

    // Este es el método que usarán los DAOs para pedir una conexión
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}