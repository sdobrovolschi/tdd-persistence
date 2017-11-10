package com.example.customerservice.infrastructure.persistence.jpa;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author Stanislav Dobrovolschi
 */
@TestConfiguration
public class DbUnitConfig {

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DataSource dataSource) {
        DatabaseDataSourceConnectionFactoryBean factory = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        factory.setDatabaseConfig(dbUnitDatabaseConfig());
        factory.setSchema("customer");
        return factory;
    }

    private DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean config = new DatabaseConfigBean();
        config.setDatatypeFactory(new MsSqlDataTypeFactory());
        return config;
    }
}
