package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name="student_email_unique", columnNames = "email")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = SEQUENCE
    )
    @Column(name ="id",
            updatable = false)
    private Long id;
    @Column(
            name ="first_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;
    @Column(
            name ="last_name",
            nullable = false)
    private String lastName;
    @Column(
            name ="email",
            nullable = false)
    private String email;
    @Column(
            name ="age",
            nullable = false)
    private Integer age;

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}
