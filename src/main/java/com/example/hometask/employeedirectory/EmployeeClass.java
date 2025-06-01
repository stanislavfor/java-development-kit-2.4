package com.example.hometask.employeedirectory;


import java.util.ArrayList;
import java.util.List;


public class EmployeeClass {
    public static class Employee {
        private final int id;
        private final String phoneNumber;
        private final String employeeName;
        private final int workExperience;

        Employee(int id, String phoneNumber, String employeeName, int workExperience) {

            this.id = id;
            this.phoneNumber = phoneNumber;
            this.employeeName = employeeName;
            this.workExperience = workExperience;
        }

        public int getId() {
            return id;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public int getWorkExperience() {
            return workExperience;
        }
    }

    private final List<Employee> employees = new ArrayList<>();

    public void employeeNew(int id, String phoneNumber, String employeeName, int workExperience) {
        employees.add(new Employee(id, phoneNumber, employeeName, workExperience));
    }

    public List<Employee> employeeExperience(int workExperience) {
        List<Employee> experiencedEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getWorkExperience() == workExperience) {
                experiencedEmployees.add(employee);
            }
        }
        return experiencedEmployees;
    }

    public List<String> employeePhone(String employeeName) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getEmployeeName().equalsIgnoreCase(employeeName)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }

    public Employee employeeId(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }


}