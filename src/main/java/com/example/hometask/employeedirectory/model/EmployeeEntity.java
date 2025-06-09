package com.example.hometask.employeedirectory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version = 0L; // Инициализация значения по умолчанию

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "work_experience")
    private Integer workExperience;

    // Конструкторы
    public EmployeeEntity(String employeeName, String phoneNumber, int workExperience) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.version = 0L; // Инициализация значения по умолчанию
    }

    // Конструктор по умолчанию (обязателен для JSON)
    public EmployeeEntity() {
        this.version = 0L; // Инициализация значения по умолчанию
    }

    public EmployeeEntity(Long id, String phoneNumber, String employeeName, Integer workExperience) {
        this.id = id;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.version = 0L; // Инициализация значения по умолчанию
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}


