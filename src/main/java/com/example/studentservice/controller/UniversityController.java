package com.example.studentservice.controller;

import com.example.studentservice.entity.University;
import com.example.studentservice.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    @PostMapping
    public University addUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

    @GetMapping
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
