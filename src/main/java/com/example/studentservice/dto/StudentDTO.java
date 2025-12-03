package com.example.studentservice.dto;

public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String universityName;

    public StudentDTO(Long id, String firstName, String lastName, String email, String universityName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.universityName = universityName;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUniversityName() { return universityName; }
}
