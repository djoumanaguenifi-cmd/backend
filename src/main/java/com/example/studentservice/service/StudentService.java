package com.example.studentservice.service;

import com.example.studentservice.dto.StudentRequestDTO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.University;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    // ➕ Ajouter un étudiant
    public Student addStudent(StudentRequestDTO dto) {
        University uni = universityRepository.findById(dto.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found"));

        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setUniversity(uni);

        return studentRepository.save(s);
    }

    // 🔄 Mettre à jour un étudiant
    public Student updateStudent(Long id, StudentRequestDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());

        if (dto.getUniversityId() != null) {
            University uni = universityRepository.findById(dto.getUniversityId())
                    .orElseThrow(() -> new RuntimeException("University not found"));
            existing.setUniversity(uni);
        }

        return studentRepository.save(existing);
    }

    // ❌ Supprimer un étudiant
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    // 📋 Lister tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 🔍 Rechercher par ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // 🔍 Rechercher par nom
    public List<Student> searchByName(String name) {
        return studentRepository.findByFirstNameContainingIgnoreCase(name);
    }

    // 🔍 Rechercher par université
    public List<Student> searchByUniversity(Long universityId) {
        University uni = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));
        return studentRepository.findByUniversity(uni);
    }
}
