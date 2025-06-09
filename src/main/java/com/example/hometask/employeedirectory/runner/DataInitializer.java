package com.example.hometask.employeedirectory.runner;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.hometask.employeedirectory.repository.EmployeeRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public DataInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (employeeRepository.count() == 0) {
            EmployeeEntity employee = new EmployeeEntity("Пётр Ивановский", "+79991234567", 5);
            employeeRepository.save(employee);
            System.out.println("Помещены исходные данные о сотруднике.");
        }
    }
}



