package com.example.hometask.employeedirectory.ui;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserInterfaceTest {

    @Mock
    private RestTemplate restTemplate;

    private Scanner scanner;

    private UserInterface userInterface;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Имитируем ввод для метода addEmployee()
        // Например: id=1, phone=12345, name=Ivan, experience=5
        String input = "1\n12345\nIvan\n5\n";
        scanner = new Scanner(input);

        userInterface = new UserInterface(scanner, restTemplate);

        // Перехватываем System.out
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        scanner.close();
    }

    @Test
    void testAddEmployeeSuccess() {
        // Если успешный ответ от restTemplate.postForEntity
        when(restTemplate.postForEntity(any(String.class), any(EmployeeEntity.class), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        userInterface.addEmployee();

        String output = outContent.toString();

        // Если сообщение об успешном добавлении данных
        assertTrue(output.contains("Сотрудник успешно добавлен."));

        // Проверяем, что restTemplate вызван с EmployeeEntity, где имя = Ivan
        ArgumentCaptor<EmployeeEntity> captor = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(restTemplate).postForEntity(any(String.class), captor.capture(), eq(Void.class));
        Assertions.assertEquals("Ivan", captor.getValue().getEmployeeName());
    }

    @Test
    void testAddEmployeeFailure() {
        // Если ответ от сервера не успешный
        when(restTemplate.postForEntity(any(String.class), any(EmployeeEntity.class), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        userInterface.addEmployee();

        String output = outContent.toString();
        assertTrue(output.contains("Ошибка при добавлении сотрудника."));
    }

    @Test
    void testAddEmployeeException() {
        // Исключение Mock при вызове restTemplate
        when(restTemplate.postForEntity(any(String.class), any(EmployeeEntity.class), eq(Void.class)))
                .thenThrow(new RuntimeException("Сбой сервера"));

        userInterface.addEmployee();

        String output = outContent.toString();
        assertTrue(output.contains("Произошла ошибка:"));
        assertTrue(output.contains("Сбой сервера"));
    }
}


//@SpringBootTest
//public class UserInterfaceTest {
//
//    @Mock
//    private Scanner scanner;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private UserInterface userInterface;
//
//    @Test
//    public void testShowMenu() throws Exception {
//        when(scanner.nextInt()).thenReturn(6); // Выход
//        userInterface.run();
//    }
//
//    @Configuration
//    static class TestConfig {
//        @Bean
//        public Scanner scanner() {
//            return new Scanner(System.in);
//        }
//
//        @Bean
//        public RestTemplate restTemplate() {
//            return new RestTemplate();
//        }
//    }
//}
