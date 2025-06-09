package com.example.hometask.employeedirectory.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Scanner;

@Component
@Profile("console") // Активируется при вызове "console"
public class ConsoleApp implements CommandLineRunner {

    private final Scanner scanner;
    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/employees";

    public ConsoleApp(Scanner scanner, RestTemplate restTemplate) {
        this.scanner = scanner;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Консольное приложение учета сотрудников ===");

        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("Выберите пункт меню: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listEmployees();
                    break;
                case "2":
                    addEmployee();
                    break;
                case "3":
                    findEmployeeById();
                    break;
                case "4":
                    deleteEmployee();
                    break;
                case "5":
                    running = false;
                    System.out.println("Завершение работы...");
                    break;
                default:
                    System.out.println("Неверный выбор. Повторите ввод.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Показать всех сотрудников");
        System.out.println("2. Добавить нового сотрудника");
        System.out.println("3. Найти сотрудника по ID");
        System.out.println("4. Удалить сотрудника по ID");
        System.out.println("5. Выход");
    }

    private void listEmployees() {
        try {
            ResponseEntity<Employee[]> response = restTemplate.getForEntity(BASE_URL, Employee[].class);
            Employee[] employees = response.getBody();

            if (employees != null && employees.length > 0) {
                System.out.println("\nСписок сотрудников:");
                for (Employee emp : employees) {
                    System.out.println(emp);
                }
            } else {
                System.out.println("Сотрудники не найдены.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка сотрудников: " + e.getMessage());
        }
    }

    private void addEmployee() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите должность: ");
        String position = scanner.nextLine();

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setPosition(position);

        try {
            ResponseEntity<Employee> response = restTemplate.postForEntity(BASE_URL, newEmployee, Employee.class);
            System.out.println("Сотрудник добавлен: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении сотрудника: " + e.getMessage());
        }
    }

    private void findEmployeeById() {
        System.out.print("Введите ID сотрудника: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            Employee emp = restTemplate.getForObject(BASE_URL + "/" + id, Employee.class);
            if (emp != null) {
                System.out.println("Найден сотрудник: " + emp);
            } else {
                System.out.println("Сотрудник не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске сотрудника: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        System.out.print("Введите ID сотрудника для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
            System.out.println("Сотрудник удален.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении сотрудника: " + e.getMessage());
        }
    }

    // Вложенный класс Employee
    static class Employee {
        private Long id;
        private String name;
        private String position;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }

        @Override
        public String toString() {
            return String.format("ID: %d | Имя: %s | Должность: %s", id, name, position);
        }
    }
}
