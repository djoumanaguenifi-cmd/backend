// src/main/java/com/example/studentservice/controller/StudentController.java
package com.example.studentservice.controller;

import com.example.studentservice.dto.StudentRequestDTO;
import com.example.studentservice.dto.StudentDTO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAllStudents().stream()
                .map(s -> new StudentDTO(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getEmail(),
                        s.getUniversity() != null ? s.getUniversity().getName() : null
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        Student s = service.getStudentById(id);
        return new StudentDTO(
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getUniversity() != null ? s.getUniversity().getName() : null
        );
    }

    @GetMapping("/university/{id}")
    public List<StudentDTO> filterByUniversity(@PathVariable Long id) {
        return service.searchByUniversity(id).stream()
                .map(s -> new StudentDTO(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getEmail(),
                        s.getUniversity() != null ? s.getUniversity().getName() : null
                ))
                .collect(Collectors.toList());
    }

   
   @PostMapping
public StudentDTO create(@Valid @RequestBody StudentRequestDTO dto) {
    Student s = service.addStudent(dto); 
    return new StudentDTO(
            s.getId(),
            s.getFirstName(),
            s.getLastName(),
            s.getEmail(),
            s.getUniversity() != null ? s.getUniversity().getName() : null
    );
}
    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @RequestBody StudentRequestDTO dto) {
        Student s = service.updateStudent(id, dto);
        return new StudentDTO(
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getUniversity() != null ? s.getUniversity().getName() : null
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}
