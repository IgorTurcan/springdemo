package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student igor = new Student(
                    "Igor",
                    "igor@mail.com",
                    LocalDate.of(1999, Month.JUNE, 20),
                    23
            );

            Student marcel = new Student(
                    "Marcel",
                    "marcel@mail.com",
                    LocalDate.of(1989, Month.FEBRUARY, 11),
                    33
            );

            repository.saveAll(List.of(igor, marcel));
        };
    }
}
