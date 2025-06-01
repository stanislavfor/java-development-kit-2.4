package com.example.hometask;

import com.example.hometask.employeedirectory.EmployeeClass;

import java.util.Scanner;
import java.util.List;


public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeClass company = new EmployeeClass();
        boolean index = false;

        while (!index) {
            System.out.print("СПИСОК СОТРУДНИКОВ ( введите + для добавления или 0 для перехода в меню ) : ");
            String command = scanner.next();
            switch (command) {
                case "+":
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите номер телефона : ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Введите имя сотрудника : ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Введите опыт работы : ");
                    int workExperience = scanner.nextInt();
                    scanner.nextLine();
                    company.employeeNew(id, phoneNumber, employeeName, workExperience);
                    break;
                case "0":
                    index = true;
                    break;
                default:
                    System.out.println("Неверная команда. Повторите ввод.");
            }
        }


        while (true) {

            System.out.print("ПОИСК СОТРУДНИКОВ ( введите 1 для продолжения или 0 для выхода ) : ");
            int choce = scanner.nextInt();
            if (choce == 0) {
                break;
            }

            else if (choce == 1) {

                System.out.print("Введите опыт работы для поиска сотрудников: ");
                int workExperienceSearch = scanner.nextInt();
                List<EmployeeClass.Employee> experiencedEmployees = company.employeeExperience(workExperienceSearch);
                if (!experiencedEmployees.isEmpty()) {
                    System.out.println("Сотрудники с опытом работы '" + workExperienceSearch + "' лет : ");
                    for (EmployeeClass.Employee employee : experiencedEmployees) {
                        System.out.println(employee.getEmployeeName() + "; ");
                    }
                } else {
                    System.out.println("Сотрудники с опытом работы '" + workExperienceSearch + "' не найдены.");
                }

                System.out.print("Введите имя сотрудника для поиска номера телефона: ");
                scanner.nextLine();
                String employeeNameSearch = scanner.nextLine();
                List<String> employeePhone = company.employeePhone(employeeNameSearch);
                if (!employeePhone.isEmpty()) {
                    System.out.println("Сотрудник '" + employeeNameSearch + "', номера телефонов : ");
                    for (String phoneNumber : employeePhone) {
                        System.out.println(phoneNumber + "; ");
                    }
                } else {
                    System.out.println("Сотрудник с именем '" + employeeNameSearch + "' не найден.");
                }

                System.out.print("Введите ID для поиска сотрудника : ");
                int idSearch = scanner.nextInt();
                EmployeeClass.Employee employee = company.employeeId(idSearch);
                if (employee != null) {
                    System.out.println("Сотрудник [" + employee.getEmployeeName() + "] с 'id = " + idSearch + "'");
                } else {
                    System.out.println("Сотрудник с id '" + idSearch + "' не найден.");
                }
            }
        }
    }


}
