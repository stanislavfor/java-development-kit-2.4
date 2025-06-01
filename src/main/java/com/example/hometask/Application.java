package com.example.hometask;

import com.example.hometask.employeedirectory.EmployeeService;
import com.example.hometask.employeedirectory.UserInterface;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeService service = new EmployeeService();
        UserInterface ui = new UserInterface(scanner, service);

        ui.run();
        ui.searchEmployees();
    }
}

