package com.saitynai.project.saitynai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DataSourceBean {
    @ConfigurationProperties(prefix = "datasource")
    @Bean
    @Primary
    public DataSource datasource() {
        return DataSourceBuilder
            .create()
            .build();
    }
}
