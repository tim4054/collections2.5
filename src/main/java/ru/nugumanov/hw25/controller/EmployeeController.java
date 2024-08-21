package ru.nugumanov.hw25.controller;

import org.springframework.web.bind.annotation.*;

import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.impl.EmployeeServiceImpl;

import java.util.Collection;

@RestController()
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/start")
    public String startPage() {
        return "Добро пожаловать";
    }

    @GetMapping("/add")
    public Employee addPerson(@RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false) String lastName,
                              @RequestParam(value = "department", required = false) int department,
                              @RequestParam(value = "salary", required = false) double salary) {
        return employeeServiceImpl.addPerson(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee findPerson(@RequestParam(value = "firstName", required = false) String firstName,
                               @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeServiceImpl.findPerson(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee deletePerson(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeServiceImpl.deletePerson(firstName, lastName);
    }

    @GetMapping("/all")
    public Collection<Employee> showAll() {
        return employeeServiceImpl.showAll();
    }
}

