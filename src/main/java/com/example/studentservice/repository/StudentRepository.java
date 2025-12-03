package com.example.studentservice.repository;

import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameContainingIgnoreCase(String name);
    List<Student> findByUniversity(University university);
}
