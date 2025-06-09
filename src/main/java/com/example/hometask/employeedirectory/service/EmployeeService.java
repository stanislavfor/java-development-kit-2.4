package com.example.hometask.employeedirectory.service;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import com.example.hometask.employeedirectory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        if (employee.getEmployeeName() == null || employee.getEmployeeName().isEmpty()) {
            throw new IllegalArgumentException("Имя сотрудника не может быть пустым");
        }
        return repository.save(employee);
    }

    public List<EmployeeEntity> findByWorkExperience(Integer experience) {
        return repository.findByWorkExperience(experience);
    }

    public List<String> findPhoneNumbersByName(String name) {
        return repository.findByEmployeeNameContaining(name)
                .stream()
                .map(EmployeeEntity::getPhoneNumber)
                .collect(Collectors.toList());
    }

    public EmployeeEntity findById(Long id) {
        Optional<EmployeeEntity> employee = repository.findById(id);
        return employee.orElseThrow(() -> new IllegalArgumentException("Сотрудник с id " + id + " не найден"));
    }

    public void removeEmployee(Long id) {
        repository.deleteById(id);
    }

    public List<EmployeeEntity> findAllEmployees() {
        return repository.findAll();
    }
}


