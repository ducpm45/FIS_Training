package com.fis.sprint_4;

import com.fis.sprint_4.service.CriminalCaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sprint4Application {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    public static CriminalCaseService criminalCaseService;
    public static void main(String[] args) {
        SpringApplication.run(Sprint4Application.class, args);
    }

}
