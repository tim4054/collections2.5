package ru.nugumanov.hw25.service;

import ru.nugumanov.hw25.models.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addPerson(String firstName, String lastName, int department, double salary);

    Employee deletePerson(String firstName, String lastName);

    Employee findPerson(String firstName, String lastName);

    Collection<Employee> showAll();

    void checkNotParameter(String firstName, String lastName);

    void checkEmptyMap();
}
