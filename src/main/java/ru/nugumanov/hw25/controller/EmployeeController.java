package ru.nugumanov.hw25.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nugumanov.hw25.Employee;
import ru.nugumanov.hw25.EmployeeService;
import ru.nugumanov.hw25.exceptions.exceptions.*;

import java.util.Collection;

@RestController()
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/start")
    public String startPage() {
        return "Добро пожаловать";
    }


    @GetMapping("/add")
    public Employee addPerson(@RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.addPerson(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findPerson(@RequestParam(value = "firstName", required = false) String firstName,
                               @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.findPerson(firstName, lastName);

    }

    @GetMapping("/remove")
    public Employee deletePerson(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.deletePerson(firstName, lastName);
    }

    @GetMapping("/all")
    public Collection<Employee> showAll() {
        return employeeService.showAll();
    }
}

