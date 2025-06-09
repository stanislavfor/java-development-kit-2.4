package com.example.hometask.employeedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        return ds;
//    }
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        // Меняем URL: база хранится в файле ./data/h2db и сохраняется между перезапусками
        ds.setUrl("jdbc:h2:file:./data/db;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
