package ru.nugumanov.hw25.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nugumanov.hw25.EmployeeService;
import ru.nugumanov.hw25.exceptions.exceptions.*;

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
    public String addPerson(@RequestParam(value = "firstName") String firstName,
                            @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.addPerson(firstName, lastName);
        } catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        } catch (EmployeeStorageIsFullException e) {
            return e.getMessage();
        } catch (EmployeeNotFoundParameter e) {
            return e.getMessage();
        }
    }

    @GetMapping("/find")
    public String findPerson(@RequestParam(value = "firstName") String firstName,
                             @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.findPerson(firstName, lastName).toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        } catch (EmployeeNotFoundParameter e) {
            return e.getMessage();
        } catch (PersonsIsEmptyExceptoin e) {
            return e.getMessage();
        }
    }

    @GetMapping("/remove")
    public String deletePerson(@RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.deletePerson(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/all")
    public String showAll() {
        try {
            return employeeService.showAll();
        } catch (PersonsIsEmptyExceptoin e) {
            return e.getMessage();
        }
    }
}

