package com.example.hometask.employeedirectory.ui;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@Component
public class UserInterface implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final Scanner scanner;

    @Autowired
    public UserInterface(Scanner scanner, RestTemplate restTemplate) {
        this.scanner = scanner;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        showWelcomeMessage();
        showMenu();
    }

    public void showWelcomeMessage() {
        System.out.println("приложение учета сотрудников\n".toUpperCase());
        System.out.println("Добро пожаловать!");
    }

    private void showMenu() {
        while (true) {
            System.out.println("Меню:\n" +
                    "1. Добавить сотрудника\n" +
                    "2. Найти сотрудников по опыту работы\n" +
                    "3. Найти телефоны по имени\n" +
                    "4. Удалить сотрудника\n" +
                    "5. Вывести СПИСОК всех сотрудников\n" +
                    "0. Выход\n" +
                    "  'Выберите и введите команду' ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Очистка входного буфера

            switch (option) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    findByWorkExperience();
                    break;
                case 3:
                    findPhoneNumbersByName();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    findAllEmployees();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    void addEmployee() {
        try {
//            System.out.print("Введите ID: ");
//            int id = scanner.nextInt();
//            scanner.nextLine(); // Очистка входного буфера

            System.out.print("Введите имя сотрудника: ");
            String employeeName = scanner.nextLine();

            System.out.print("Введите номер телефона: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Введите опыт работы: ");
            int workExperience = scanner.nextInt();
            scanner.nextLine(); // Очистка входного буфера

            EmployeeEntity employee = new EmployeeEntity(employeeName, phoneNumber, workExperience);
            ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:8080/api/employees", employee, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Сотрудник успешно добавлен.");
            } else {
                System.out.println("Ошибка при добавлении сотрудника.");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }


    private void findByWorkExperience() {
        try {
            System.out.print("Введите опыт работы для поиска сотрудников: ");
            int workExperience = scanner.nextInt();
            scanner.nextLine(); // Очистка входного буфера

            EmployeeEntity[] employeesArray = restTemplate.getForObject(
                    "http://localhost:8080/api/employees/experience/{experience}",
                    EmployeeEntity[].class,
                    workExperience);

            if (employeesArray != null && employeesArray.length > 0) {
                List<EmployeeEntity> employees = List.of(employeesArray);
                System.out.println("Сотрудники с опытом работы  (полных лет) '" + workExperience + "' :");
                for (EmployeeEntity emp : employees) {
                    System.out.println(emp.getEmployeeName() + ", Опыт работы (полных лет): " + emp.getWorkExperience());
                }
            } else {
                System.out.println("Сотрудники с таким опытом работы не найдены.");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Сотрудники с таким опытом работы не найдены.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }


    private void findPhoneNumbersByName() {
        try {
            System.out.print("Введите имя сотрудника для поиска номера телефона: ");
            String name = scanner.nextLine();

            String[] phoneNumbersArray = restTemplate.getForObject(
                    "http://localhost:8080/api/employees/name/{name}",
                    String[].class,
                    name);

            if (phoneNumbersArray != null && phoneNumbersArray.length > 0) {
                List<String> phoneNumbers = List.of(phoneNumbersArray);
                System.out.println("Телефонные номера сотрудника '" + name + "':");
                for (String number : phoneNumbers) {
                    System.out.println(number);
                }
            } else {
                System.out.println("Сотрудник с таким именем не найден.");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Сотрудник с таким именем не найден.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }


    private void deleteEmployee() {
        try {
            System.out.print("Введите ID сотрудника для удаления: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Очистка входного буфера

            restTemplate.delete("http://localhost:8080/api/employees/{id}", id);
            System.out.println("Сотрудник удалён.");
        } catch (HttpClientErrorException e) {
            System.out.println("Сотрудник с таким ID не найден.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void findAllEmployees() {
        try {
            EmployeeEntity[] employeesArray = restTemplate.getForObject(
                    "http://localhost:8080/api/employees",
                    EmployeeEntity[].class);

            if (employeesArray != null && employeesArray.length > 0) {
                List<EmployeeEntity> employees = List.of(employeesArray);
                System.out.println("Список всех сотрудников:");
                for (EmployeeEntity emp : employees) {
                    System.out.println("ID: " + emp.getId() + ", Имя: " + emp.getEmployeeName() + ", Телефон: " + emp.getPhoneNumber() + ", Опыт работы (полных лет): " + emp.getWorkExperience());
                }
            } else {
                System.out.println("Сотрудники не найдены.");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

}



