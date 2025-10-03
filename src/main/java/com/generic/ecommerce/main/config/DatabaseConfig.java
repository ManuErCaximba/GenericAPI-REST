package com.generic.ecommerce.main.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url:}")
    private String datasourceUrl;

    @PostConstruct
    public void ensureSqliteDirectoryExists() {
        try {
            if (datasourceUrl != null && datasourceUrl.startsWith("jdbc:sqlite:")) {
                String raw = datasourceUrl.substring("jdbc:sqlite:".length());
                int q = raw.indexOf('?');
                if (q > -1) raw = raw.substring(0, q);
                Path dbPath = Paths.get(raw).toAbsolutePath();
                Path parent = dbPath.getParent();
                if (parent != null && !Files.exists(parent)) {
                    Files.createDirectories(parent);
                }
            }
        } catch (Exception ignored) {
            // If creating the directory fails, let SQLite handle errors later
        }
    }
}
