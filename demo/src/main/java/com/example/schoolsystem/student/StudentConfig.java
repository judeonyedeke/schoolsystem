package com.example.schoolsystem.student;

import com.example.schoolsystem.student.Data.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner runner(StudentRepository repository){
        return args -> {
                    String email = "";
                    Student student1 = new Student(
                            "Jude",
                            LocalDate.of(1995, Month.MAY,25),
                            email = "word4wordwithjude@gmail.com"

            );
                    Student student2 = new Student(
                            "Immaculate",
                            LocalDate.of(1990, Month.FEBRUARY,5),
                            email = "jaycieo5@gmail.com"
            );
            repository.saveAll(
                    List.of(student1, student2));
        };
    }
}
