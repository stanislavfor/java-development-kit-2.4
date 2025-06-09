package com.example.hometask.employeedirectory.controller;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import com.example.hometask.employeedirectory.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee) {
        EmployeeEntity savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/experience/{experience}")
    public ResponseEntity<List<EmployeeEntity>> findByWorkExperience(@PathVariable Integer experience) {
        List<EmployeeEntity> employees = employeeService.findByWorkExperience(experience);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<String>> findPhoneNumbersByName(@PathVariable String name) {
        List<String> phoneNumbers = employeeService.findPhoneNumbersByName(name);
        return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> findById(@PathVariable Long id) {
        EmployeeEntity employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.removeEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> findAllEmployees() {
        List<EmployeeEntity> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}


