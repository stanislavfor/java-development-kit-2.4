package com.example.hometask.employeedirectory.controller;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import com.example.hometask.employeedirectory.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testAddEmployee() throws Exception {
        EmployeeEntity employee = new EmployeeEntity("John Doe", "1234567890", 5);
        when(employeeService.addEmployee(any(EmployeeEntity.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeName").value("John Doe"));

        verify(employeeService).addEmployee(any(EmployeeEntity.class));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<EmployeeEntity> employees = List.of(
                new EmployeeEntity("Alice", "111", 3),
                new EmployeeEntity("Bob", "222", 4)
        );
        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        EmployeeEntity employee = new EmployeeEntity("Alice", "111", 3);
        employee.setId(1L);
        when(employeeService.findById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Alice"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());

        verify(employeeService).removeEmployee(1L);
    }

    @Test
    void testFindPhoneNumbersByName() throws Exception {
        List<String> phones = List.of("111", "222");
        when(employeeService.findPhoneNumbersByName("A")).thenReturn(phones);

        mockMvc.perform(get("/employees/name/A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testFindByExperience() throws Exception {
        List<EmployeeEntity> employees = List.of(
                new EmployeeEntity("E1", "000", 5)
        );
        when(employeeService.findByWorkExperience(5)).thenReturn(employees);

        mockMvc.perform(get("/employees/experience/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].workExperience").value(5));
    }
}
