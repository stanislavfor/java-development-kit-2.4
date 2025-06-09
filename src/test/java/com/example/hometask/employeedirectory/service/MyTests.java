package com.example.hometask.employeedirectory.service;

import com.example.hometask.employeedirectory.controller.EmployeeController;
import com.example.hometask.employeedirectory.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MyTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testBeans() {
        assertNotNull(context.getBean(EmployeeService.class));
        assertNotNull(context.getBean(EmployeeRepository.class));
        assertNotNull(context.getBean(EmployeeController.class));
    }
}
