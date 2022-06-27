package com.fis.sprint_3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class JDBCTemplateConfig {
    @Configuration
    @ComponentScan(basePackages = {"com.fis.sprint_3"})
    public class JdbcTemplateConfig {
    }
}
