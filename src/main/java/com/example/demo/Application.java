package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student floky = new Student(
                    "Floky",
                    "Poky",
                    "floky.poky@mail.fr",
                    11);

            Student floky2 = new Student(
                    "Floky",
                    "Flow",
                    "flow@mail.fr",
                    11);

            Student toto = new Student(
                    "Toto",
                    "Titi",
                    "toto@mail.fr",
                    21
            );

            Student jiji = new Student(
                    "JIJI",
                    "JOJO",
                    "jiji@mail.com",
                    28
            );

            studentRepository.saveAll(List.of(floky,floky2,toto,jiji));

            System.out.println("Number Of Students : "+ studentRepository.count());

            Optional<Student> student1 = studentRepository.findById(1L);
            if(!student1.isPresent()){
                System.out.println("Student with ID 1 not found");
            }

            Optional<Student> student2 = studentRepository.findStudentByEmail("jiji@mail.com");
            if(!student2.isPresent()){
                System.out.println("Student with ID 2 not found");
            }

            studentRepository.
                    findStudentWithNameAndAgeGreaterOrEqual("Floky", 11).
                    forEach(System.out::println);

            studentRepository.
                    findStudentWithNameAndAgeGreaterOrEqualNative("Floky", 11).
                    forEach(System.out::println);

            System.out.println("Select all students");
            studentRepository.findAll()
                    .forEach(System.out::println);

            System.out.println("Delete Floky id = 1");
            studentRepository.deleteById(1L);

            System.out.println("Number Of Students : "+ studentRepository.count());
        };
    }
}
