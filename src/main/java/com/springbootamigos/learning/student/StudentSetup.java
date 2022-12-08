package com.springbootamigos.learning.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentSetup {

    @Bean
    public CommandLineRunner runner(StudentRepo studentRepo) {

        return args -> {

            studentRepo.saveAll(Student.sampleData);
        };
    }
}
