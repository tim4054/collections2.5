package ru.nugumanov.hw25.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nugumanov.hw25.EmployeeService;

@RestController
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String startPage(){
        return "Добро пожаловать";
    }

    @GetMapping("/employee/add")
    public String addPerson(@RequestParam (value = "firstName") String firstName,
                            @RequestParam (value = "lastName") String lastName) {
        String result = employeeService.addPerson(firstName, lastName);
        return result;
    }
    @GetMapping("/employee/find")
    public String findPerson(@RequestParam (value = "firstName") String firstName,
                             @RequestParam (value = "lastName") String lastName){
        employeeService.findPerson(firstName, lastName);
        return employeeService.findPerson(firstName, lastName).toString();
    }

}

