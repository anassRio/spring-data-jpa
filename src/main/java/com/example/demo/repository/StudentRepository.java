package com.example.demo.repository;

import com.example.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT * FROM Student s where s.email=?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT * FROM Student s where s.firstName=?1 AND s.age >= ?2")
    List<Student> findStudentWithNameAndAgeGreaterOrEqual(String firstName, Integer age);

    @Query( value = "SELECT * FROM student where first_name = :firstName AND age >= :age",
            nativeQuery = true)
    List<Student> findStudentWithNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query ("DELETE FROM Student std WHERE std.id =?1")
    int deleteStudentById(Long id);

}
