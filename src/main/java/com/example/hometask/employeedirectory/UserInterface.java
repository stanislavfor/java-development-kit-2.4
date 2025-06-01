package com.example.hometask.employeedirectory;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private EmployeeService service;

    public UserInterface(Scanner scanner, EmployeeService service) {
        this.scanner = scanner;
        this.service = service;
    }

    // Метод для добавления новых сотрудников
    public void handleAddEmployee() {
        System.out.print("Введите ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Проверка наличия сотрудника с таким же ID
        if (service.findById(id) != null) {
            System.out.println("Ошибка! Сотрудник с таким ID уже существует!");
            return;
        }

        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Введите имя сотрудника: ");
        String employeeName = scanner.nextLine();

        System.out.print("Введите опыт работы: ");
        int workExperience = scanner.nextInt();
        scanner.nextLine();

        service.addEmployee(id, phoneNumber, employeeName, workExperience);
    }

    // Обработка команды выхода
    public boolean isExitCommand(String command) {
        return "+".equals(command) || "0".equals(command);
    }

    // Основной цикл обработки команд
    public void run() {
        while (true) {
            System.out.print("СПИСОК СОТРУДНИКОВ (введите + для добавления СПИСКА или 0 для перехода в МЕНЮ): ");
            String command = scanner.next();

            switch (command) {
                case "+":
                    handleAddEmployee();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверная команда. Повторите ввод.");
            }
        }
    }

    // Обработка запроса на поиск сотрудников
    public void searchEmployees() {
        while (true) {
            System.out.print("ПОИСК СОТРУДНИКОВ (введите 1 для поиска, 2 для добавления СПИСКА или 0 для ВЫХОДА): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }
            else if (choice == 2) {
                handleAddEmployee();
            }
            else if (choice == 1) {
                System.out.print("Введите опыт работы для поиска сотрудников: ");
                int workExperienceSearch = scanner.nextInt();
                List<EmployeeService.Employee> experiencedEmployees = service.findByWorkExperience(workExperienceSearch);

                if (!experiencedEmployees.isEmpty()) {
                    System.out.println("Сотрудники с опытом работы '" + workExperienceSearch + "' лет: ");
                    for (EmployeeService.Employee employee : experiencedEmployees) {
                        System.out.println(employee.getEmployeeName() + ";");
                    }
                } else {
                    System.out.println("Сотрудники с опытом работы '" + workExperienceSearch + "' не найдены.");
                }

                scanner.nextLine(); // очищаем буфер
                System.out.print("Введите имя сотрудника для поиска номера телефона: ");
                String employeeNameSearch = scanner.nextLine();
                List<String> employeePhones = service.findPhoneNumbersByName(employeeNameSearch);

                if (!employeePhones.isEmpty()) {
                    System.out.println("Сотрудник '" + employeeNameSearch + "', номера телефонов: ");
                    for (String phoneNumber : employeePhones) {
                        System.out.println(phoneNumber + ";");
                    }
                } else {
                    System.out.println("Сотрудник с именем '" + employeeNameSearch + "' не найден.");
                }

                System.out.print("Введите ID для поиска сотрудника: ");
                int idSearch = scanner.nextInt();
                EmployeeService.Employee foundEmployee = service.findById(idSearch);

                if (foundEmployee != null) {
                    System.out.println("Сотрудник [" + foundEmployee.getEmployeeName() +
                            "] с 'id=" + idSearch + "'");
                } else {
                    System.out.println("Сотрудник с id '" + idSearch + "' не найден.");
                }
            }
        }
    }
}