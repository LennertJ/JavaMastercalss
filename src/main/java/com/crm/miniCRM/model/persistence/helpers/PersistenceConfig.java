package com.crm.miniCRM.model.persistence.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Bean
    public DataSource dataSource() {
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/miniCRM?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8");
        dataSource.setUsername("Mathy");
        dataSource.setPassword("Password");
        return dataSource;*/

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("embedded");
        dataSource.setPassword("embedded");
        dataSource.setUrl("jdbc:h2:~/example/EmbeddedDb;INIT=create schema if not exists Queue\\;");

        return dataSource;
    }
}