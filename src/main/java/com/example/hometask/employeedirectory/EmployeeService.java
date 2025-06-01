package com.example.hometask.employeedirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для управления списком сотрудников.
 */
public class EmployeeService {

    /**
     * Хранит список сотрудников.
     */
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     */
    public EmployeeService() {}

    /**
     * Добавляет нового сотрудника в базу.
     *
     * @param id              уникальный идентификатор сотрудника
     * @param phoneNumber     контактный номер телефона сотрудника
     * @param employeeName    полное имя сотрудника
     * @param workExperience  стаж работы сотрудника в годах
     */
    public void addEmployee(int id, String phoneNumber, String employeeName, int workExperience) {
        employees.add(new Employee(id, phoneNumber, employeeName, workExperience));
    }

    /**
     * Возвращает список сотрудников с заданным стажем работы.
     *
     * @param workExperience стаж работы сотрудников, которых нужно найти
     * @return список сотрудников с таким стажем
     */
    public List<Employee> findByWorkExperience(int workExperience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getWorkExperience() == workExperience) {
                result.add(employee);
            }
        }
        return result;
    }

    /**
     * Возвращает телефонные номера сотрудников с указанным именем.
     *
     * @param name имя сотрудника, которого нужно найти
     * @return список номеров телефонов соответствующих сотрудников
     */
    public List<String> findPhoneNumbersByName(String name) {
        List<String> phones = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getEmployeeName().equalsIgnoreCase(name)) {
                phones.add(employee.getPhoneNumber());
            }
        }
        return phones;
    }

    /**
     * Возвращает сотрудника по уникальному идентификатору.
     *
     * @param id уникальный идентификатор сотрудника
     * @return сотрудник с данным идентификатором или null, если такой сотрудник отсутствует
     */
    public Employee findById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Внутренний класс Employee для хранения информации о сотруднике.
     */
    public static class Employee {
        private final int id;
        private final String phoneNumber;
        private final String employeeName;
        private final int workExperience;

        /**
         * Создает новый экземпляр сотрудника.
         *
         * @param id              уникальный идентификатор сотрудника
         * @param phoneNumber     контактный номер телефона сотрудника
         * @param employeeName    полное имя сотрудника
         * @param workExperience  стаж работы сотрудника в годах
         */
        Employee(int id, String phoneNumber, String employeeName, int workExperience) {
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.employeeName = employeeName;
            this.workExperience = workExperience;
        }

        /**
         * Получает уникальный идентификатор сотрудника.
         *
         * @return идентификатор сотрудника
         */
        public int getId() {
            return id;
        }

        /**
         * Получает контактный номер телефона сотрудника.
         *
         * @return номер телефона сотрудника
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * Получает полное имя сотрудника.
         *
         * @return имя сотрудника
         */
        public String getEmployeeName() {
            return employeeName;
        }

        /**
         * Получает стаж работы сотрудника.
         *
         * @return стаж работы сотрудника в годах
         */
        public int getWorkExperience() {
            return workExperience;
        }
    }
}